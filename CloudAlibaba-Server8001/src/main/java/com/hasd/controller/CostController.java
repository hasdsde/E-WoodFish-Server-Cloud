package com.hasd.controller;

import com.hasd.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/22 14:36
 **/

@RestController
@RequestMapping("/cost")
public class CostController {
    
    @GetMapping("/one")
    public Result CostOne() {
        return Result.success();
    }
}
