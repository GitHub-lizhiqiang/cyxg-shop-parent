package com.fh.permission.service.impl;

import com.fh.permission.entity.UmsPermission;
import com.fh.permission.mapper.UmsPermissionMapper;
import com.fh.permission.service.IUmsPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户权限表 服务实现类
 * </p>
 *
 * @author lzq
 * @since 2021-05-13
 */
@Service
public class UmsPermissionServiceImpl extends ServiceImpl<UmsPermissionMapper, UmsPermission> implements IUmsPermissionService {

    @Autowired
    private UmsPermissionMapper permissionMapper;
    @Override
    public void saveOrUpdatePerm(UmsPermission umsPermission) {
        if (umsPermission.getId()==null){
            umsPermission.setCreateTime(new Date());
        }
        if (umsPermission.getPid()==null){
            umsPermission.setPid(0l);
        }
        saveOrUpdate(umsPermission);
    }

    @Override
    public List<String> getPermissionByUserId(Long userId) {
        return permissionMapper.getPermissionByUserId(userId);
    }
}
