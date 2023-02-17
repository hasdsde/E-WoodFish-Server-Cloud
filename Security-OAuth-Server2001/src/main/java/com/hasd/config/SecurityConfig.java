package com.hasd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/16 13:34
 **/

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().permitAll()
//                .and().exceptionHandling().authenticationEntryPoint(new UnAuthEntryPoint())
                .and().authorizeRequests().antMatchers("/login/*", "/oauth/*", "/logout/*").permitAll()
                .and().authorizeRequests().anyRequest().permitAll()
                .and().logout().logoutUrl("/logout")
                .and().csrf().disable();
    }
}
