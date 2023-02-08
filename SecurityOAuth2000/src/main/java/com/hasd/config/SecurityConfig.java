package com.hasd.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/8 12:40
 **/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().permitAll()
//                .and().exceptionHandling().authenticationEntryPoint(new UnAuthEntryPoint())//无权限处理方法
                .and().authorizeRequests().antMatchers("/test/ant").permitAll()
                .anyRequest().authenticated()//默认拦截所有请求
                //不拦截的路径
                .and().logout().logoutUrl("/logout")//退出登录路径
                .and().csrf().disable();//关闭csrf
    }
}
