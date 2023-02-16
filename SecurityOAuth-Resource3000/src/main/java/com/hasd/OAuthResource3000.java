package com.hasd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/9 17:13
 **/

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OAuthResource3000 {
    public static void main(String[] args) {
        SpringApplication.run(OAuthResource3000.class, args);
    }
}
