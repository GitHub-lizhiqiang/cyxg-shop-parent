package com.fh.login;

import com.fh.admin.entity.UmsAdmin;
import com.fh.login.token.CreateTokenUtils;
import com.fh.login.token.TokenCommons;
import com.fh.result.ResponseEnum;
import com.fh.result.ResultUtils;
import com.fh.security.AdminUserDetails;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/admin/login")
public class LoginController {
    @Autowired
    private CreateTokenUtils createTokenUtils;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HttpServletRequest request;
    @PostMapping
    public ResultUtils LoginAction(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return ResultUtils.error(ResponseEnum.IS_NULL);
        }
        /*UmsAdmin admin= adminService.getByUsername(username);
        if(admin==null){
            return ResultUtils.error(ResponseEnum.ACCOUNT_NOT_EXIST);
        }
        if(!MD5Util.encoder(password).equals(admin.getPassword())){
            return ResultUtils.error(ResponseEnum.WRING_PASSWORD);
        }*/
        UserDetails userDetails= userDetailsService.loadUserByUsername(username);
        //判断密码是否正确
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new BadCredentialsException("用户名密码错误");
        }
      /*  UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);*/
        //生成token
        AdminUserDetails adminUserDetails=(AdminUserDetails)userDetails;
        UmsAdmin admin = adminUserDetails.getUmsAdmin();
        String token= createTokenUtils.createToken(admin);
        String typeName=TokenCommons.getDeviceType(request);
        String typeTokenKey=TokenCommons.typeTokenKey(admin.getUsername(),typeName);
        Set<String> set=redisTemplate.keys(typeTokenKey);
        if(set.size()>0){
            redisTemplate.delete(set);
        }
        redisTemplate.opsForValue().set(TokenCommons.redisTokenKey(admin.getUsername(),token,typeName),System.currentTimeMillis(),
                TokenCommons.exp_time, TimeUnit.MINUTES);
        return ResultUtils.success(token);
    }
    @GetMapping
    public ResultUtils queryLoginout(){
        String token = request.getHeader("Authorization-token");
        ResultUtils resultUtils= createTokenUtils.resolverToken(token);
        Claims claims = (Claims) resultUtils.getData();
        String username = (String) claims.get("username");
        String typeName = TokenCommons.getDeviceType(request);
        String key = TokenCommons.setTokenKey(username,token,typeName);
        //根据key删除redis中的用户信息
        redisTemplate.delete(key);
        String adminKey= TokenCommons.redisUser(username,"admin");
        String roleKey= TokenCommons.redisUser(username,"role");
        String permissionKey= TokenCommons.redisUser(username,"permission");
        redisTemplate.delete(adminKey);
        redisTemplate.delete(roleKey);
        redisTemplate.delete(permissionKey);
        return ResultUtils.success();
    }
}
