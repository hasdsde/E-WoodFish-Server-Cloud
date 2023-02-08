package com.hasd.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/8 12:38
 **/

@Data
@ToString
public class User {
    private String username = "hutao";
    private String password = "123456";
    private String role = "Admin";
    private String nickName;
    private String salt;
    private String token;
}
