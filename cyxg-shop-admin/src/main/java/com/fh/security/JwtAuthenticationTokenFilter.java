package com.fh.security;

import com.fh.login.token.CreateTokenUtils;
import com.fh.login.token.TokenCommons;
import com.fh.result.ResultUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.fh.login.token.TokenCommons.exp_UserRedis;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private CreateTokenUtils createTokenUtils;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization-token");
        if(token!=null){
           ResultUtils resultUtils= createTokenUtils.resolverToken(token);
         //判断token值是否有效
           if(resultUtils.getCode()==200){
               Claims claims = (Claims) resultUtils.getData();
               String username = (String) claims.get("username");
               String typeName = TokenCommons.getDeviceType(request);
               String redisTokenKey = TokenCommons.redisTokenKey(username, token, typeName);
               if(redisTemplate.hasKey(redisTokenKey)&& SecurityContextHolder.getContext().getAuthentication()==null){
                   //创建spring security的userNamePasswordToken?
                   UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                   UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                   SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                   redisTemplate.expire(redisTokenKey,TokenCommons.exp_time, TimeUnit.MINUTES);

                   String adminKey= TokenCommons.redisUser(username,"admin");
                   String roleKey= TokenCommons.redisUser(username,"role");
                   String permissionKey= TokenCommons.redisUser(username,"permission");
                   redisTemplate.expire(adminKey,TokenCommons.exp_time, TimeUnit.MINUTES);
                   redisTemplate.expire(roleKey,TokenCommons.exp_time, TimeUnit.MINUTES);
                   redisTemplate.expire(permissionKey,TokenCommons.exp_time, TimeUnit.MINUTES);

               }
           }
        }
        filterChain.doFilter(request,response);

    }

}
