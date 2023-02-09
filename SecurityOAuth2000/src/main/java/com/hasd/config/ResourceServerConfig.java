//package com.hasd.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
//
///**
// * @author : hasd
// * @version 1.0.0
// * @since : 2023/2/9 10:43
// **/
//
//@EnableResourceServer
//@Configuration
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//    //    @Bean //令牌管理实例
//    public ResourceServerTokenServices resourceServerTokenServices() {
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        remoteTokenServices.setCheckTokenEndpointUrl("http://127.0.0.1:2000/oauth/check_token");
//        remoteTokenServices.setClientId("c1");
//        remoteTokenServices.setClientSecret("secret");
//        return remoteTokenServices;
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.formLogin().permitAll().and() //这句必要，就是说不拦截登录请求
//                .authorizeRequests()
//                .antMatchers("/login/**").permitAll()
//                .antMatchers("/oauth/**").permitAll()
////                .antMatchers("/**").access("#oauth2.hasScope('all')") //拦截所有请求。放行必须有all权限
//                .and().csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources
//                .resourceId("c1") //需要与客户端id保持一致
//                .tokenServices(resourceServerTokenServices())
//                .stateless(true);//仅允许token验证方法校验
//    }
//
//}
