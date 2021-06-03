package com.fh.security;

import com.fh.admin.entity.UmsAdmin;
import com.fh.admin.service.IUmsAdminService;
import com.fh.login.token.TokenCommons;
import com.fh.permission.service.IUmsPermissionService;
import com.fh.role.service.IUmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//让spring Security核心配置文件生效
@EnableWebSecurity
//Security检验授权的注解生效
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   @Autowired
   private IUmsAdminService adminService;
   @Autowired
   private IUmsPermissionService permissionService;
   @Autowired
   private IUmsRoleService roleService;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RedisTemplate redisTemplate;



   //充当拦截器和过滤器的方法，拦截请求
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**","/upload","/userPhoto/**").permitAll()
                .anyRequest().authenticated();//其它请求都要被拦截，需要登录认证
        http.csrf().disable()//关闭csrf拦截
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().cacheControl();
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //当登陆认证失败时或者没有权限访问时要返回自定的json数据
        http.exceptionHandling().accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
        //自定过滤器处理token


    }
    //授权
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    //查询登录用户所拥有的角色和权限，并以返回
    protected UserDetailsService userDetailsService() {
        return username->{
            String userKey= TokenCommons.redisUser(username,"admin");
            String roleKey= TokenCommons.redisUser(username,"role");
            String permissionKey= TokenCommons.redisUser(username,"permission");
            UmsAdmin umsAdmin = null;
            if (redisTemplate.hasKey(userKey)){
                umsAdmin= (UmsAdmin) redisTemplate.opsForValue().get(userKey);
            }else {
                umsAdmin=adminService.getByUsername(username);
                redisTemplate.opsForValue().set(userKey,umsAdmin,TokenCommons.exp_time, TimeUnit.MINUTES);
            }

            if (umsAdmin!=null){
                Long userId = umsAdmin.getId();
                List<String>roleList=new ArrayList<>();
                if (redisTemplate.hasKey(roleKey)){
                    roleList= (List<String>) redisTemplate.opsForValue().get(roleKey);
                }else {
                    roleList= roleService.getRoleByUserId(userId);
                    redisTemplate.opsForValue().set(roleKey,roleList,TokenCommons.exp_time, TimeUnit.MINUTES);
                }
                List<String>permissionList= new ArrayList<>();
                if (redisTemplate.hasKey(permissionKey)){
                    permissionList= (List<String>) redisTemplate.opsForValue().get(permissionKey);
                }else {
                    permissionList= permissionService.getPermissionByUserId(userId);
                    redisTemplate.opsForValue().set(permissionKey,permissionList,TokenCommons.exp_time, TimeUnit.MINUTES);
                }


                return new AdminUserDetails(permissionList,roleList,umsAdmin);
            }else {
                throw new UsernameNotFoundException("用户不存在");
            }

        };

    };

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }
    @Bean
    //密码校验方法
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean//进行认证管理，管理来自不同方向的认证管理
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
