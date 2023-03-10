package com.hasd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/22 14:02
 **/

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Service8001 {
    public static void main(String[] args) {
        SpringApplication.run(Service8001.class, args);
    }
}
