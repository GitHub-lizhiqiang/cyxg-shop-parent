package com.fh.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.admin.entity.UmsAdmin;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author lzq
 * @since 2021-05-11
 */
public interface IUmsAdminService extends IService<UmsAdmin> {

    IPage<UmsAdmin> queryPage(Page<UmsAdmin> page);
    void saveUserOrUpdate(UmsAdmin umsAdmin);

    UmsAdmin getByUsername(String username);
}
