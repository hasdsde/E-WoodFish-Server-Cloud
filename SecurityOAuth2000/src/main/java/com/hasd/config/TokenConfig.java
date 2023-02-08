package com.hasd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/8 18:10
 **/

@Configuration
public class TokenConfig {
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
}
