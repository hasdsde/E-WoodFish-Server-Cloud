package com.hasd.controller;

import com.hasd.entity.User;
import com.hasd.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
}
