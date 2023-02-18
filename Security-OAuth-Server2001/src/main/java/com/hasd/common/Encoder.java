package com.hasd.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/18 14:38
 **/


public class Encoder {
    public static void main(String[] args) {
        String code = new BCryptPasswordEncoder().encode("secret");
        System.out.println(code);
    }
}
