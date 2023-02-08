package com.hasd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/8 10:52
 **/

@RestController
@RequestMapping("/test")
public class TestController {
    public String test() {
        return "test Success Port:82";
    }
}
