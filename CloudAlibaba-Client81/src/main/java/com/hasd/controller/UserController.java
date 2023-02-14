package com.hasd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hasd.config.NormalException;
import com.hasd.entity.Code;
import com.hasd.entity.Result;
import com.hasd.entity.User;
import com.hasd.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/14 14:44
 **/

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserMapper userMapper;

    @GetMapping("/test")
    public String test() {
        List<User> users = userMapper.selecAll();
        User user = users.get(2);
        System.out.println(user);
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
}
