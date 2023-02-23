package com.hasd.service;

import com.hasd.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/23 09:53
 **/

@Component
@FeignClient(value = "CloudAlibaba-Service")
public interface UserFeignService {

    @GetMapping("/user/chain")
    Result getChain();
}
