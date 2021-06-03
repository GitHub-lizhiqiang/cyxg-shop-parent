package com.fh.rolepermission.controller;


import com.fh.Vo.PermissionToRole;
import com.fh.result.ResultUtils;
import com.fh.rolepermission.service.IUmsRolepermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzq
 * @since 2021-05-14
 */
@RestController
@RequestMapping("/ums/rolepermission")
public class UmsRolepermissionController {
    @Autowired
    private IUmsRolepermissionService umsRolepermissionService;

    @PutMapping
    public ResultUtils savePeimToRole(@RequestBody PermissionToRole permissionToRole){
        umsRolepermissionService.savePeimToRole(permissionToRole);
        return ResultUtils.success();

    }

}
