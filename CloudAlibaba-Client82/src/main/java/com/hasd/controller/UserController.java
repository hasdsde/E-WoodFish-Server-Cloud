package com.hasd.controller;

import com.hasd.entity.User;
import com.hasd.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author hasd
 * @since 2023-02-14
 */
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
}
