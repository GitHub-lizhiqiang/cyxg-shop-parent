package com.fh.rolepermission.service;

import com.fh.Vo.PermissionToRole;
import com.fh.rolepermission.entity.UmsRolepermission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzq
 * @since 2021-05-14
 */
public interface IUmsRolepermissionService extends IService<UmsRolepermission> {

    void savePeimToRole(PermissionToRole permissionToRole);
}
