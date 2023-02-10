package com.hasd.config;

import com.hasd.entity.CustomTokenEnhancer;
import com.hasd.service.MyUserDetailService;
import org.springframework.context.annotation.Bean;
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
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/8 13:54
 **/

@Configuration
@EnableAuthorizationServer //启用以配置OAuth2授权服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    MyUserDetailService userDetailService;

    @Resource
    private TokenStore tokenStore;

    @Resource
    private ClientDetailsService clientDetailsService;

    @Resource
    private AuthorizationCodeServices authorizationCodeServices;
    @Resource//奇怪autowired会报错
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Resource
    private CustomTokenEnhancer customTokenEnhancer;

    @Bean//设置令牌属性
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);
        services.setSupportRefreshToken(true);
        services.setTokenStore(tokenStore);
        services.setAccessTokenValiditySeconds(7200);
        services.setRefreshTokenValiditySeconds(259200);

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(jwtAccessTokenConverter));
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(customTokenEnhancer, jwtAccessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);
        return services;
    }

    @Override//客户端详情信息，可以将其写死或者使用数据库
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
//        clients.inMemory() //使用内存,先写死配置
//                .withClient("c1") //客户端ID
//                .secret(new BCryptPasswordEncoder().encode("secret"))//客户端秘钥
//                .resourceIds("r1")
//                .authorizedGrantTypes("authorization_code", "implicit", "password", "client_credentials", "refresh_token")//授权范围
//                .scopes("all")//授权范围
//                .autoApprove(false)//自动返回授权码
//                .redirectUris("https://www.baidu.com"); //重定向URL
        //这里也可以设置令牌属性
//                .accessTokenValiditySeconds(client.getTokenValid()) //token时长
//                .refreshTokenValiditySeconds(client.getFlushTokenValid());//刷新token时长
    }

    @Override//配置令牌token访问端点和令牌服务
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenServices(tokenServices())
                .userDetailsService(userDetailService)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
        //自定义授权页面,替换用的URL
//        endpoints.pathMapping("/oauth/confirm_access", "/custom/confirm_access");
    }

    @Override//配置令牌端点的安全约束
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //下面两个设置为完全公开
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();//允许表单验证
//                //开启/oauth/token_key验证端口认证权限访问
//                .tokenKeyAccess("isAuthenticated()")
//                //开启/oauth/check_token验证端口认证权限访问
//                .checkTokenAccess("isAuthenticated()")
//                //允许表单认证
//                .allowFormAuthenticationForClients();
    }


}
