package com.hasd.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.hasd.entity.Result;
import com.hasd.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/22 14:12
 **/

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    RabbitTemplate rabbitTemplate;

    @PostMapping("/reg")
    public Result userReg(@RequestBody User user) {
        JSON json = JSONUtil.parse(user);
        rabbitTemplate.convertAndSend("topics", "reg", json);
        return Result.success();
    }

    @PostMapping("/login")
    public Result userLogin(@RequestBody User user) {
        JSON json = JSONUtil.parse(user);
        rabbitTemplate.convertAndSend("topics", "login", json);
        return Result.success();
    }

    @PostMapping("/top")
    public Result userTop() {
        rabbitTemplate.convertAndSend("topics", "top", "");
        return Result.success();
    }

}