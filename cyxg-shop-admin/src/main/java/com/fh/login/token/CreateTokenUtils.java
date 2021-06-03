package com.fh.login.token;

import com.fh.admin.entity.UmsAdmin;
import com.fh.result.ResponseEnum;
import com.fh.result.ResultUtils;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class CreateTokenUtils {
    @Value("${jwt.token.signature}")
    private String signature;
    @Value("${jwt.token.alg}")
    private String alg;
    @Value("${jwt.token.type}")
    private String type;
    @Value("${jwt.token.exptime}")
    private Long   exptime;
    public String createToken(UmsAdmin admin){
        String token="";
        //jwt的组成部分；
        //(1)加密的方式类型头部信息
        Map<String,Object> headMap=new HashMap<>();
        headMap.put("alg",alg);
        headMap.put("type",type);
        //(2)有效载荷
        HashMap<String, Object> payLoadMap = new HashMap<>();
        payLoadMap.put("username",admin.getUsername());
        payLoadMap.put("id",admin.getId());
        payLoadMap.put("nickName",admin.getNickName());
        Long expTime=System.currentTimeMillis()+exptime;
        token= Jwts.builder().setHeader(headMap)
                .setClaims(payLoadMap).setExpiration(new Date(expTime))
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        return token;
    }

    public ResultUtils resolverToken(String token){
        HashMap<Object, Object> map = new HashMap<>();
        if(StringUtils.isNotBlank(token)){
            try {
                Claims claims=Jwts.parser()
                        .setSigningKey(signature)
                        .parseClaimsJws(token)
                        .getBody();
                System.out.println("username");
                return ResultUtils.success(claims);
            }catch (SignatureException e){
                System.out.println("无效签名");
            }catch (ExpiredJwtException e){
                System.out.println("token失效");
            }catch (Exception e){
                System.out.println("token值字符串不符合规则");
                e.printStackTrace();
            }
        }
        return ResultUtils.error(ResponseEnum.TOKEN_INVALID);
    }

}
