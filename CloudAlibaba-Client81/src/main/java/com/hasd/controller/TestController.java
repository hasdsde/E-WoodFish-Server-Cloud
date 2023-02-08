package com.hasd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/7 14:46
 **/

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/test")
    public String JustTest() {
        return "test Success Port:82";
    }
}
