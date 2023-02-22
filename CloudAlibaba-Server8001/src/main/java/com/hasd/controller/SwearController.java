package com.hasd.controller;

import cn.hutool.json.JSONObject;
import com.hasd.entity.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/22 14:31
 **/

@RestController
@RequestMapping("/logs")
public class SwearController {
    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping("/swear")
    public Result UserSwear(@RequestParam("username") String username, @RequestParam("score") Integer score) {
        JSONObject swear = new JSONObject();
        swear.put("username", username);
        swear.put("score", score);
        rabbitTemplate.convertAndSend("topics", "swear", swear);
        return Result.success();
    }
}
