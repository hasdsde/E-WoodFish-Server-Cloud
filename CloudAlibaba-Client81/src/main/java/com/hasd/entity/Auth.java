package com.hasd.entity;

import lombok.*;

import java.util.List;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/18 17:05
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Auth {
    private String access_token;
    private String refresh_token;
    private User user_info;
    private List<String> scoop;
    private String token_type;
    private String expires_in;
}
