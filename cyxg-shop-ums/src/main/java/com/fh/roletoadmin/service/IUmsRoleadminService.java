package com.fh.roletoadmin.service;

import com.fh.Vo.RoleToAdmin;
import com.fh.roletoadmin.entity.UmsRoleadmin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzq
 * @since 2021-05-13
 */
public interface IUmsRoleadminService extends IService<UmsRoleadmin> {

    void saveUmsRoleAdmin(RoleToAdmin roleToAdmin);
}
