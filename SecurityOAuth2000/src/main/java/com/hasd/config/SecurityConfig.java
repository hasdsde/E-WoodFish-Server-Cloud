package com.hasd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

import javax.sql.DataSource;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/8 12:40
 **/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean//认证管理器
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder());
        return jdbcClientDetailsService;
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        User user = new User();
//        auth.inMemoryAuthentication()
//                .withUser(user.getUsername())
//                .password("{noop}" + user.getPassword())
//                .roles(user.getRole());
//    }

    @Override//安全拦截机制
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().permitAll()
//                .and().exceptionHandling().authenticationEntryPoint(new UnAuthEntryPoint())//无权限处理方法
                .and().authorizeRequests().antMatchers("/login/*", "/oauth/*", "/logout/*").permitAll()
//                .anyRequest().authenticated()//默认拦截所有请求
                //不拦截的路径
                .and().logout().logoutUrl("/logout")//退出登录路径
                .and()
                .csrf().disable();//关闭csrf
//                .addFilter(new LoginFilter());//登录过滤器
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        //不会要求认证的路径
//        web.ignoring().antMatchers("/test/ant");
//    }

}
