package com.fh.permission.mapper;

import com.fh.permission.entity.UmsPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 后台用户权限表 Mapper 接口
 * </p>
 *
 * @author lzq
 * @since 2021-05-13
 */
public interface UmsPermissionMapper extends BaseMapper<UmsPermission> {

    List<String> getPermissionByUserId(Long userId);
}
