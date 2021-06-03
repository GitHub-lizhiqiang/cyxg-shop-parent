package com.fh.usertomenu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.result.ResultUtils;
import com.fh.usertomenu.entity.MenuToUser;
import com.fh.usertomenu.entity.UmsUsertomenu;
import com.fh.usertomenu.service.IUmsUsertomenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzq
 * @since 2021-05-17
 */
@RestController
@RequestMapping("/ums/usertomenu")
public class UmsUsertomenuController {
    @Autowired
    private IUmsUsertomenuService umsUsertomenuService;

    @GetMapping
    public List<Map<String,Object>> UserToMenu(){
        return umsUsertomenuService.UserToMenu();
    }
    @PostMapping
    public ResultUtils SaveMenuToUser(@RequestBody MenuToUser menuToUser){
        umsUsertomenuService.SaveMenuToUser(menuToUser);
        return ResultUtils.success();
    }
    @GetMapping("{userId}")
    public ResultUtils queryMenuByUser(@PathVariable("userId") Long userId){
        QueryWrapper<UmsUsertomenu> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userId",userId);
        queryWrapper.select("menuId");
       List<UmsUsertomenu> list= umsUsertomenuService.list(queryWrapper);
       List<Long> MenuList=list.stream().map(x->x.getMenuid()).collect(Collectors.toList());
        return ResultUtils.success(MenuList);
    }

}
