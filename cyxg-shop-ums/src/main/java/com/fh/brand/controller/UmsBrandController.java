package com.fh.brand.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.brand.entity.UmsBrand;
import com.fh.brand.service.IUmsBrandService;
import com.fh.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzq
 * @since 2021-05-20
 */
@RestController
@RequestMapping("/ums/brand")
public class UmsBrandController {
    @Autowired
    private IUmsBrandService umsBrandService;
    @GetMapping
    public ResultUtils queryUmsBrand(Page<UmsBrand> page){
        QueryWrapper<UmsBrand> queryWrapper = new QueryWrapper<UmsBrand>();
        queryWrapper.orderByDesc("sort");
        umsBrandService.page(page,queryWrapper);
        return ResultUtils.success(page);
    }


    @PostMapping
    public ResultUtils saveOrUpdateBrand(@RequestBody UmsBrand brand){
        umsBrandService.saveOrUpdate(brand);
        return ResultUtils.success();
    }

    @GetMapping("/{brandId}")
    public ResultUtils    getBrandById(@PathVariable("brandId")Long   brandId){
        UmsBrand brand = umsBrandService.getById(brandId);
        return ResultUtils.success(brand);
    }
    @DeleteMapping
    public ResultUtils deleteBach(@RequestBody List<Long> idList){
        umsBrandService.removeByIds(idList);
        return ResultUtils.success();
    }

    @DeleteMapping("/{umsId}")
    public ResultUtils deleteBach(@PathVariable("umsId") Long umsId){
        umsBrandService.removeById(umsId);
        return ResultUtils.success();
    }

}
