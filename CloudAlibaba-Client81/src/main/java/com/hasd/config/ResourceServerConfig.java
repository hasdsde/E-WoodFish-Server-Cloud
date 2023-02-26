package com.hasd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/20 14:25
 **/

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Bean
    public ResourceServerTokenServices resourceServerTokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setClientId("cloud-client81");
        services.setCheckTokenEndpointUrl("http://127.0.0.1:2001/oauth/check_token");
        services.setClientSecret("secret");
        return services;
    }

    @Resource
    ResourceServerTokenServices resourceServerTokenServices;

    @Resource
    TokenStore tokenStore;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("client").stateless(true)
                .tokenServices(resourceServerTokenServices)
                .tokenStore(tokenStore)
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/user/access_token").permitAll()
                .anyRequest().access("#oauth2.hasScope('ROLE_USER')")
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
    }
}
