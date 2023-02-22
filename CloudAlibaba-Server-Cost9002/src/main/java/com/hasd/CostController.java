package com.hasd;

import com.hasd.entity.Result;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/22 15:47
 **/

@Component
public class CostController {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue, exchange = @Exchange(name = "topics", type = "topic"), key = {"cost.*"})
    })
    public Result costItem() {
        return Result.success();
    }
    

}
