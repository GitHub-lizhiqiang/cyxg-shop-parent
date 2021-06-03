package com.fh.permission.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.Vo.Search;
import com.fh.permission.entity.UmsPermission;
import com.fh.permission.service.IUmsPermissionService;
import com.fh.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 后台用户权限表 前端控制器
 * </p>
 *
 * @author lzq
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/ums/permission")
public class UmsPermissionController {
    @Autowired
    private IUmsPermissionService umsPermissionService;

    @GetMapping
    public IPage<UmsPermission> queryPermsList(Page<UmsPermission> umsPermissionPage, Search search){
        QueryWrapper<UmsPermission> queryWrapper=new QueryWrapper<UmsPermission>();
        if(search.getPid()!=0){
            queryWrapper.eq("pid",search.getPid());
        }else if(search.getName()!=""){
           queryWrapper.like("name",search.getName());
        }else{
            queryWrapper.eq("pid",0);
        }
        return umsPermissionService.page(umsPermissionPage,queryWrapper);
    }
    @RequestMapping("queryList")
    public List<UmsPermission> queryList(){
        QueryWrapper<UmsPermission> queryWrapper=new QueryWrapper<UmsPermission>();
        queryWrapper.eq("pid",0);
        return umsPermissionService.list(queryWrapper);
    }
    @RequestMapping("querySonList")
    public List<UmsPermission> querySonList(){
        QueryWrapper<UmsPermission> queryWrapper=new QueryWrapper<UmsPermission>();
        queryWrapper.notIn("pid",0);
        return umsPermissionService.list(queryWrapper);
    }

    @PutMapping
    public ResultUtils saveOrUpdatePerm(@RequestBody UmsPermission umsPermission){
        umsPermissionService.saveOrUpdatePerm(umsPermission);
        return ResultUtils.success();
    }
    @GetMapping("{id}")
    public ResultUtils getById(@PathVariable("id") Long id){
        UmsPermission umsPermission= umsPermissionService.getById(id);
        return ResultUtils.success(umsPermission);
    }
    @DeleteMapping("{id}")
    public ResultUtils DeletePerm(@PathVariable("id") Long id){
         umsPermissionService.removeById(id);
         return ResultUtils.success();
    }

}
