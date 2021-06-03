package com.fh.rolepermission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.fh.Vo.PermissionToRole;
import com.fh.rolepermission.entity.UmsRolepermission;
import com.fh.rolepermission.mapper.UmsRolepermissionMapper;
import com.fh.rolepermission.service.IUmsRolepermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.roletoadmin.entity.UmsRoleadmin;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzq
 * @since 2021-05-14
 */
@Service
public class UmsRolepermissionServiceImpl extends ServiceImpl<UmsRolepermissionMapper, UmsRolepermission> implements IUmsRolepermissionService {

    @Override
    public void savePeimToRole(PermissionToRole permissionToRole) {
        QueryWrapper<UmsRolepermission> queryWrapper=new QueryWrapper<UmsRolepermission>();
        queryWrapper.eq("roleId",permissionToRole.getRoleId());
        remove(queryWrapper);

        if(CollectionUtils.isNotEmpty(permissionToRole.getPermissionIdList())){
            List<UmsRolepermission> umsRolepermissionList=new ArrayList<UmsRolepermission>();
            permissionToRole.getPermissionIdList().forEach(permissionId->{
                UmsRolepermission rolepermission = new UmsRolepermission();
                rolepermission.setRoleid(permissionToRole.getRoleId());
                rolepermission.setPermissionid(permissionId);
                umsRolepermissionList.add(rolepermission);
            });
            saveBatch(umsRolepermissionList);
        }
    }
}
