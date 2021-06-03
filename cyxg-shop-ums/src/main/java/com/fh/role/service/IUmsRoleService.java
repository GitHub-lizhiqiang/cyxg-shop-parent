package com.fh.role.service;

import com.fh.role.entity.UmsRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author lzq
 * @since 2021-05-13
 */
public interface IUmsRoleService extends IService<UmsRole> {

    void saveUserOrUpdate(UmsRole umsRole);

    List<String> getRoleByUserId(Long userId);
}
