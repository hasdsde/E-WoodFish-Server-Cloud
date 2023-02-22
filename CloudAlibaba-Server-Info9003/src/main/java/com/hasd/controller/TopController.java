package com.hasd.controller;

import com.hasd.entity.Result;
import com.hasd.entity.User;
import com.hasd.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/22 15:27
 **/

@RestController
@RequestMapping("/user")
public class TopController {
    @Resource
    UserMapper userMapper;

    private Result getTop() {
        List<User> users = userMapper.selectTop();
        return Result.success(users);
    }
}
