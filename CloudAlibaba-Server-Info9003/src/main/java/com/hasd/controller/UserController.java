package com.hasd.controller;

import com.hasd.entity.Result;
import com.hasd.entity.User;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/22 15:18
 **/

@Component
public class UserController {

    @Resource
    RabbitTemplate rabbitTemplate;

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue, exchange = @Exchange(name = "topics", type = "topic"), key = {"user.reg"})
    })
    public Result Reg(@RequestBody User user) {
        return Result.success(user);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue, exchange = @Exchange(name = "topics", type = "topic"), key = {"user.login"})
    })
    public Result Login(@RequestBody User user) {
        return Result.success(user);
    }


}
