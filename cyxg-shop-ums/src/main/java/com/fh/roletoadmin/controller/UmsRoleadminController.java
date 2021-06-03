package com.fh.roletoadmin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.Vo.RoleToAdmin;
import com.fh.result.ResultUtils;
import com.fh.role.entity.UmsRole;
import com.fh.role.service.IUmsRoleService;
import com.fh.roletoadmin.entity.UmsRoleadmin;
import com.fh.roletoadmin.service.IUmsRoleadminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzq
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/ums/roleadmin")
public class UmsRoleadminController {
    @Autowired
    private IUmsRoleadminService umsRoleadminService;
    @Autowired
    private IUmsRoleService umsRoleService;

    @GetMapping("getList")
    public List<UmsRole> getRoleAdminList(){
        return umsRoleService.list();
    }
    @PutMapping
    public ResultUtils saveUmsRoleAdmin(@RequestBody RoleToAdmin roleToAdmin){
        umsRoleadminService.saveUmsRoleAdmin(roleToAdmin);
        return ResultUtils.success();
    }
    @GetMapping("{userId}")
    public ResultUtils queryRoleByUser(@PathVariable("userId") Long userId){
        QueryWrapper<UmsRoleadmin> queryWrapper=new QueryWrapper<UmsRoleadmin>();
        queryWrapper.eq("userId",userId);
        queryWrapper.select("roleId");
        List<UmsRoleadmin> list = umsRoleadminService.list(queryWrapper);
        List<Long> roleIdList = list.stream().map(x->x.getRoleid()).collect(Collectors.toList());
        return ResultUtils.success(roleIdList);
    }

}
