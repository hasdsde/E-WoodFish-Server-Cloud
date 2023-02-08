package com.hasd.entity;

import lombok.Data;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/8 12:35
 **/

@Data
public class Client {
    private String ClientId = "client_hutao";
    private String ClientSecret = "secret_hutao";
    private String scoop = "Admin";
    private int tokenValid = 60 * 30 * 4;
    private int flushTokenValid = 60 * 30 * 4;
    private String[] grantTypes = {"authorization_code", "implicit", "password", "client_credentials"};
}
