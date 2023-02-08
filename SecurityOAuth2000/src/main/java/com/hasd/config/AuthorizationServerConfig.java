package com.hasd.config;

import com.hasd.entity.Client;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/8 13:54
 **/

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        Client client = new Client();
        clients.inMemory().withClient(client.getClientId()) //客户端ID
                .secret("{noop}" + client.getClientSecret())//客户端秘钥
                .autoApprove(true)//自动返回授权码
                .redirectUris("https://www.baidu.com") //重定向URL
                .scopes(client.getScoop())//授权范围
                .accessTokenValiditySeconds(client.getTokenValid()) //token时长
                .refreshTokenValiditySeconds(client.getFlushTokenValid())//刷新token时长
                .authorizedGrantTypes(client.getGrantTypes());//认证权限
    }

}
