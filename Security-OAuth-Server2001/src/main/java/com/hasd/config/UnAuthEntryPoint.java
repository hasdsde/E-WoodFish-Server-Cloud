package com.hasd.config;

import cn.hutool.json.JSONObject;
import com.hasd.entity.Code;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", Code.CODE_FORBIDDEN);
        jsonObject.put("msg", "未认证" + e);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().println(jsonObject);
    }
}
