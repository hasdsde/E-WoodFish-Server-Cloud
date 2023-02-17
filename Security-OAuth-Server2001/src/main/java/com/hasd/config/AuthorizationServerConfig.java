package com.hasd.config;

import com.hasd.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import javax.annotation.Resource;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/16 13:53
 **/

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    ClientDetailsService clientDetailsService;

    @Resource
    AuthenticationManager authenticationManager;
    @Resource
    AuthorizationCodeServices authorizationCodeServices;

    @Resource
    @Qualifier(value = "tokenServices")
    AuthorizationServerTokenServices authorizationServerTokenServices;

    @Resource
    MyUserDetailService myUserDetailService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenServices(authorizationServerTokenServices)
                .userDetailsService(myUserDetailService)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }
}
