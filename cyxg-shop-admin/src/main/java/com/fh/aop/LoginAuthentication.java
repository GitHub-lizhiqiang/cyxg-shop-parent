package com.fh.aop;

import com.fh.login.token.CreateTokenUtils;
import com.fh.login.token.TokenCommons;
import com.fh.result.ResponseEnum;
import com.fh.result.ResultUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

public class LoginAuthentication {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CreateTokenUtils createTokenUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    @Around(value ="execution(* com.fh.*.controller.*.*(..))")
    public Object aroundLoginAuth(ProceedingJoinPoint joinPoint){
        Object obj=null;
        try {
            String token=request.getHeader("Authorization-token");
            if(StringUtils.isBlank(token)){
                obj= ResultUtils.error(ResponseEnum.TOKEN_INVALID);
                return obj;
            }
            ResultUtils resultUtils=createTokenUtils.resolverToken(token);
            if(resultUtils.getCode()!=200){
                return resultUtils;
            }
            Claims claims= (Claims) resultUtils.getData();
            String username= (String) claims.get("username");
            String typeName= TokenCommons.getDeviceType(request);
            String redisKey=TokenCommons.redisTokenKey(username,token,typeName);
            if(!redisTemplate.hasKey(redisKey)){
                obj= ResultUtils.error(ResponseEnum.TOKEN_INVALID);
                return obj;
            }
            redisTemplate.expire(redisKey,TokenCommons.exp_time, TimeUnit.MINUTES);
            obj=joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }
}
