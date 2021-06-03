package com.fh.security;

import com.fh.admin.entity.UmsAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//UserDetails为用户主体，从中获取登录用户的信息
//重写框架提供的方法，使用自定义方法
public class AdminUserDetails implements UserDetails {
  private List<String> permissionList;
  private List<String> roleList;
  private UmsAdmin umsAdmin;

   public AdminUserDetails(List<String> permissionList,List<String> roleList,UmsAdmin umsAdmin){
       this.permissionList=permissionList;
       this.roleList=roleList;
       this.umsAdmin=umsAdmin;
   }
   public AdminUserDetails(){

   }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<SimpleGrantedAuthority> list=new ArrayList<>();
       permissionList.forEach(permission->{
           list.add(new SimpleGrantedAuthority(permission));
       });
       roleList.forEach(role->{
           list.add(new SimpleGrantedAuthority("ROLE_"+role));
       });
       return list;
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }
    //所有方法返回值都必须为true，否则返回无数据。为NULL，
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        return umsAdmin.getStatus().equals(1);
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public UmsAdmin getUmsAdmin() {
        return umsAdmin;
    }
}
