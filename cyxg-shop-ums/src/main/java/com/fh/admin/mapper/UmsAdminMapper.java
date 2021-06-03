package com.fh.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.admin.entity.UmsAdmin;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author lzq
 * @since 2021-05-11
 */
@Repository
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    IPage<UmsAdmin> queryPage(IPage<UmsAdmin> page);
}
