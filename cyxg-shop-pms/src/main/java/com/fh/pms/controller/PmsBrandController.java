package com.fh.pms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.pms.entity.BrandBo;
import com.fh.pms.entity.PmsBrand;
import com.fh.pms.service.IPmsBrandService;
import com.fh.result.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author lzq
 * @since 2021-05-22
 */
@RestController
@RequestMapping("/brands")
public class PmsBrandController {

    @Autowired
    private IPmsBrandService brandService;

    @GetMapping
    public ResultUtils queryBrandList(BrandBo brandBo){
        QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper<PmsBrand>();
        if(StringUtils.isNotBlank(brandBo.getName())){
            queryWrapper.like("name",'%'+brandBo.getName()+'%');
        }
        return ResultUtils.success(brandService.page(brandBo,queryWrapper));
    }
    @PostMapping
    public ResultUtils saveOrupdateBrand(@RequestBody PmsBrand pmsBrand){
        return brandService.saveOrupdateBrand(pmsBrand);
    }
    @GetMapping("/{brandId}")
    public ResultUtils getBrandById(@PathVariable("brandId") Long brandId){
        return brandService.getBrandById(brandId);
    }
    @DeleteMapping("/{brandId}")
    public ResultUtils deleteBrandById(@PathVariable("brandId") Long brandId){
        return brandService.deleteBrandById(brandId);
    }




}
