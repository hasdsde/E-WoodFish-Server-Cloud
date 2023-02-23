package com.hasd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/23 08:51
 **/
@Slf4j
@RestController
public class UserController {
    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue, exchange = @Exchange(value = "topics", type = "topic"),
                    key = {"user.chain"})
    })
    public void getTop(String message) {
        log.info("接收到信息: " + message);
    }
}
