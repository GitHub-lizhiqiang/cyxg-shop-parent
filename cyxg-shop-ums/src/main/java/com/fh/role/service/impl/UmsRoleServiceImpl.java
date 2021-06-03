package com.fh.role.service.impl;

import com.fh.md5.MD5Util;
import com.fh.role.entity.UmsRole;
import com.fh.role.mapper.UmsRoleMapper;
import com.fh.role.service.IUmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author lzq
 * @since 2021-05-13
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {
    @Autowired
    private UmsRoleMapper umsRoleMapper;
    @Override
    public void saveUserOrUpdate(UmsRole umsRole) {
        if(umsRole.getId()==null){
            umsRole.setCreateTime(new Date());
        }
        saveOrUpdate(umsRole);

    }

    @Override
    public List<String> getRoleByUserId(Long userId) {
        return umsRoleMapper.getRoleByUserId(userId);
    }
}
