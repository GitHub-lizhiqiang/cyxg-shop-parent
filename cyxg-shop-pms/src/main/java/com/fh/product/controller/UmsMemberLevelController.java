package com.fh.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.product.entity.UmsMemberLevel;
import com.fh.product.service.IUmsMemberLevelService;
import com.fh.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 会员等级表 前端控制器
 * </p>
 *
 * @author lzq
 * @since 2021-05-25
 */
@RestController
@RequestMapping("/product/member-level")
public class UmsMemberLevelController {
    @Autowired
    private IUmsMemberLevelService memberLevelService;
    @GetMapping
    public ResultUtils getList(){
        QueryWrapper<UmsMemberLevel> queryWrapper=new QueryWrapper<UmsMemberLevel>();
        queryWrapper.eq("default_status",0);
        queryWrapper.orderByDesc("growth_point");
        List<UmsMemberLevel> list = memberLevelService.list(queryWrapper);
        return ResultUtils.success(list);
    }

}
