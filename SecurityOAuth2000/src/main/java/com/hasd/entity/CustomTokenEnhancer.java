package com.hasd.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/10 17:23
 **/

@Component
public class CustomTokenEnhancer implements TokenEnhancer {
    @Resource
    ObjectMapper objectMapper; //com.fasterxml.jackson.databind.ObjectMapper;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        HashMap<String, Object> additionalInfo = new HashMap<>();
        Object principal = oAuth2Authentication.getPrincipal();

        try {
            String s = objectMapper.writeValueAsString(principal);
            Map map = objectMapper.readValue(s, Map.class);
            map.remove("password");
            map.remove("authorities");
            map.remove("accountNonExpired");
            map.remove("accountNonLocked");
            map.remove("credentialsNonExpired");
            map.remove("enabled");
            additionalInfo.put("user_info", map);
            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);//这是什么东西
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return oAuth2AccessToken;
    }
}
