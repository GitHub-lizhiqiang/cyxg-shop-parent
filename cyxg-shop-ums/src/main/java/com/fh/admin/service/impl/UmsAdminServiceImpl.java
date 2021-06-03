package com.fh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.admin.entity.UmsAdmin;
import com.fh.admin.mapper.UmsAdminMapper;
import com.fh.admin.service.IUmsAdminService;
import com.fh.md5.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author lzq
 * @since 2021-05-11
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements IUmsAdminService {

    @Autowired
    private UmsAdminMapper umsAdminMapper;
    @Override
    public IPage<UmsAdmin> queryPage(Page<UmsAdmin> page) {
        return umsAdminMapper.queryPage(page);
    }
    @Override
    public void saveUserOrUpdate(UmsAdmin umsAdmin){
        if(umsAdmin.getId()==null){
            umsAdmin.setCreateTime(new Date());
            umsAdmin.setPassword(MD5Util.encoder("123456"));
        }
        saveOrUpdate(umsAdmin);
    }

    @Override
    public UmsAdmin getByUsername(String username) {
        QueryWrapper<UmsAdmin> queryWrapper=new QueryWrapper();
        queryWrapper.eq("username",username);
        return getOne(queryWrapper);
    }
}
