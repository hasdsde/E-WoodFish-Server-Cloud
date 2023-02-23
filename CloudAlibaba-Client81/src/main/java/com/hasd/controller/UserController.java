package com.hasd.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hasd.config.NormalException;
import com.hasd.entity.Auth;
import com.hasd.entity.Code;
import com.hasd.entity.Result;
import com.hasd.entity.User;
import com.hasd.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/14 14:44
 **/


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${server.port}")
    private String SERVER_PORT;

    //重复调用问题
    @Resource
    RestTemplate restTemplate;

    private String CLIENT_ID;

    @PostConstruct
    private void init() {
        CLIENT_ID = "cloud-client" + SERVER_PORT;
    }

    private final String LOCAL_URI = "http://localhost";
    private final String REDIRECT_URL = LOCAL_URI + ":" + SERVER_PORT + "/user/access_code";

    @Resource
    UserMapper userMapper;

    @PreAuthorize("hasAnyAuthority('user')")
    @GetMapping("/chain")
    public String test() {
        return "success";
    }

    @PostMapping("/reg")
    public Result register(@RequestBody User user, HttpServletRequest request) {
        User userExist = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (userExist == null) {
            throw new NormalException(Code.CODE_SERVER_ERROR, "用户名已存在");
        }
        user.setCreateTime(LocalDateTime.now());
        user.setRegIp(request.getRemoteAddr());
        int i = userMapper.insert(user);
        if (i != 1) {
            throw new NormalException(Code.CODE_SERVER_ERROR, "注册失败");
        }
        return Result.success("注册成功");
    }


    @GetMapping("/login")
    public String loginToOAuth() {
        String url = LOCAL_URI + ":" + SERVER_PORT + "/oauth/authorize";
        return "forward:" + url + "??client_id=" + CLIENT_ID + "&response_type=code&scope=user&redirect_uri=" + REDIRECT_URL;
    }

    @GetMapping("/access_token")
    public Result accessCode(@RequestParam("code") String code) {
        log.info("code=" + code);
        String url = "http://127.0.0.1:2001/oauth/token?" +
                "code=" + code +
                "&grant_type=" + "authorization_code" +
                "&client_id=" + CLIENT_ID +
                "&client_secret=" + "secret" +
                "&redirect_uri=" + LOCAL_URI + ":" + SERVER_PORT + "/user/access_token";

        try {
            JSONObject result = restTemplate.postForObject(url, null, JSONObject.class);
            if (result != null) {
                log.info("result:" + result);
                Auth auth = result.toBean(Auth.class);
                HashMap<String, Object> map = new HashMap<>();
                map.put("access_token", auth.getAccess_token());
                map.put("refresh_token", auth.getRefresh_token());
                map.put("username", auth.getUser_info().getUsername());
                return Result.success(map);
            }
        } catch (Exception e) {
            throw new NormalException("access_error" + e.toString(), Code.CODE_AUTH_ERROR);
        }
        return Result.error(Code.CODE_AUTH_ERROR, "认证失败");
    }
}
