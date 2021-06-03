package com.fh.security;

import com.alibaba.fastjson.JSON;
import com.fh.result.ResponseEnum;
import com.fh.result.ResultUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//权限认证不通过返回的JSON
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        if(e instanceof BadCredentialsException){
            response.getWriter().println(JSON.toJSONString(ResultUtils.error(ResponseEnum.WRING_PASSWORD)));
        }else if(e instanceof UsernameNotFoundException){
            response.getWriter().println(JSON.toJSONString(ResultUtils.error(ResponseEnum.ACCOUNT_NOT_EXIST)));
        }else{
            response.getWriter().println(JSON.toJSONString(ResultUtils.error(ResponseEnum.TOKEN_INVALID)));
        }

        response.getWriter().flush();

    }
}
