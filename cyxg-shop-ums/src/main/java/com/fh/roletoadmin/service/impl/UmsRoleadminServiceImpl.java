package com.fh.roletoadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.Delete;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.fh.Vo.RoleToAdmin;
import com.fh.roletoadmin.entity.UmsRoleadmin;
import com.fh.roletoadmin.mapper.UmsRoleadminMapper;
import com.fh.roletoadmin.service.IUmsRoleadminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzq
 * @since 2021-05-13
 */
@Service
public class UmsRoleadminServiceImpl extends ServiceImpl<UmsRoleadminMapper, UmsRoleadmin> implements IUmsRoleadminService {

    @Override
    @Transactional
    public void saveUmsRoleAdmin(RoleToAdmin roleToAdmin) {
        QueryWrapper<UmsRoleadmin> queryWrapper=new QueryWrapper<UmsRoleadmin>();
            queryWrapper.eq("userId",roleToAdmin.getUserId());
            remove(queryWrapper);

        if(CollectionUtils.isNotEmpty(roleToAdmin.getRoleIdList())){
            List<UmsRoleadmin> umsRoleadminList=new ArrayList<UmsRoleadmin>();
            roleToAdmin.getRoleIdList().forEach(roleId->{
                UmsRoleadmin roleadmin = new UmsRoleadmin();
                roleadmin.setUserid(roleToAdmin.getUserId());
                roleadmin.setRoleid(roleId);
                umsRoleadminList.add(roleadmin);
            });
            saveBatch(umsRoleadminList);
        }

    }
}
