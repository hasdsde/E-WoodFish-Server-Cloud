package com.hasd.entity;

import lombok.Data;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/8 12:38
 **/

@Data
public class User {
    private String userName = "hutao";
    private String passWord = "123456";
    private String role = "Admin";
}
