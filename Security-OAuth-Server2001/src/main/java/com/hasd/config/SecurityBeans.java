package com.hasd.config;

import com.hasd.entity.MyTokenEnhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/16 20:19
 **/

@Configuration
public class SecurityBeans {


    public ClientDetailsService clientDetailsService;

    @Resource
    public TokenStore tokenStore;

    @Resource
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Resource
    MyTokenEnhancer myTokenEnhancer;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder());
        return jdbcClientDetailsService;
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);
    }


    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);
        services.setSupportRefreshToken(true);
        services.setTokenStore(tokenStore);
        services.setAccessTokenValiditySeconds(7200);
        services.setRefreshTokenValiditySeconds(259200);

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter, myTokenEnhancer));
        services.setTokenEnhancer(tokenEnhancerChain);
        return services;
    }


    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        String SIGNING_KEY = "123";
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }
}
