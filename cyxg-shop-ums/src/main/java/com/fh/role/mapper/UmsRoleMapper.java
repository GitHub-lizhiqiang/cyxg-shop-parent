package com.fh.role.mapper;

import com.fh.role.entity.UmsRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author lzq
 * @since 2021-05-13
 */
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    List<String> getRoleByUserId(Long userId);
}
