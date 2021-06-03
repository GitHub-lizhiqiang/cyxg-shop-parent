package com.fh.role.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.result.ResultUtils;
import com.fh.role.entity.UmsRole;
import com.fh.role.service.IUmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 后台用户角色表 前端控制器
 * </p>
 *
 * @author lzq
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/ums/role")
public class UmsRoleController {
    @Autowired
    private IUmsRoleService umsRoleService;

    @GetMapping
    public IPage<UmsRole> queryList(Page<UmsRole> page){
        return umsRoleService.page(page);
    }

    @PutMapping
    public ResultUtils addUms(@RequestBody UmsRole umsRole){
        umsRoleService.saveUserOrUpdate(umsRole);
        return ResultUtils.success();
    }
    @GetMapping("/{id}")
    public ResultUtils getById(@PathVariable("id") Long id){

        UmsRole umsRole=  umsRoleService.getById(id);
        return ResultUtils.success(umsRole);
    }

    @DeleteMapping("/{id}")
    public void deleteUms(@PathVariable("id")Long id){
        umsRoleService.removeById(id);
    }


}
