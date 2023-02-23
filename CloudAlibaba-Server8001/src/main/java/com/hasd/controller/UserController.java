package com.hasd.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.hasd.entity.Result;
import com.hasd.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/22 14:12
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping("/chain")
    public Result ChainTest() {
        rabbitTemplate.convertAndSend("topics", "user.chain", "haha");
        return Result.success();
    }

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

    @GetMapping("/top")
    public Result userTop() {
        rabbitTemplate.convertAndSend("topics", "top", "");
        return Result.success();
    }

}
