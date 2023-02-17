package com.hasd.config;

import com.hasd.entity.Code;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/16 13:43
 **/


public class UnAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        throw new NormalException("没有权限访问", Code.CODE_FORBIDDEN);
    }
}
