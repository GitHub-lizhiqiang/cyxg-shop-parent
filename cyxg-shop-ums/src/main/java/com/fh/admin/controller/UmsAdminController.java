package com.fh.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.admin.entity.UmsAdmin;
import com.fh.admin.service.IUmsAdminService;

import com.fh.result.ResultUtils;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author lzq
 * @since 2021-05-11
 */
@RestController
@RequestMapping("/admin/ums")
public class UmsAdminController {
    @Resource
    private IUmsAdminService umsAdminService;

    @GetMapping
    @PreAuthorize("hasAuthority('dwadwa')")
    public ResultUtils queryList(Page<UmsAdmin> page){
        umsAdminService.page(page);
        return ResultUtils.success(page);
    }

    @PutMapping
    public ResultUtils addUms(@RequestBody UmsAdmin umsAdmin){
        umsAdminService.saveUserOrUpdate(umsAdmin);
        return ResultUtils.success();
    }
    @GetMapping("/{id}")
    public ResultUtils getById(@PathVariable("id") Long id){

        UmsAdmin umsAdmin=  umsAdminService.getById(id);
        return ResultUtils.success(umsAdmin);
    }

    @DeleteMapping("/{id}")
    public void deleteUms(@PathVariable("id")Long id){
        umsAdminService.removeById(id);
    }

}
