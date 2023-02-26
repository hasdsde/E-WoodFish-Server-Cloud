package com.hasd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/7 14:45
 **/
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@SpringBootApplication
public class Client81 {
    public static void main(String[] args) {
        SpringApplication.run(Client81.class, args);
    }
}
