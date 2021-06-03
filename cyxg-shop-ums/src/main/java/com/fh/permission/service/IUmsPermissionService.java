package com.fh.permission.service;

import com.fh.permission.entity.UmsPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台用户权限表 服务类
 * </p>
 *
 * @author lzq
 * @since 2021-05-13
 */
public interface IUmsPermissionService extends IService<UmsPermission> {

    void saveOrUpdatePerm(UmsPermission umsPermission);

    List<String> getPermissionByUserId(Long userId);
}
