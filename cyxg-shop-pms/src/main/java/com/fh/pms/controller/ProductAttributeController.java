package com.fh.pms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.pms.entity.ProductAttribute;
import com.fh.pms.entity.ProductAttributeBo;
import com.fh.pms.service.IProductAttributeService;
import com.fh.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author lzq
 * @since 2021-05-22
 */
@RestController
@RequestMapping("/productAttributes")
public class ProductAttributeController {

    @Autowired
    private IProductAttributeService productAttributeService;


    @GetMapping
    public ResultUtils queryList(ProductAttributeBo productAttributeBo){
        QueryWrapper<ProductAttribute> productAttributeBoQueryWrapper = new QueryWrapper<ProductAttribute>();
        if(productAttributeBo.getPid()!=null){
            productAttributeBoQueryWrapper.eq("product_attribute_category_id",productAttributeBo.getPid());
        }
        if(productAttributeBo.getType()!=null){
            productAttributeBoQueryWrapper.eq("type",productAttributeBo.getType());
        }
        productAttributeService.page(productAttributeBo,productAttributeBoQueryWrapper);
        return ResultUtils.success(productAttributeBo);
    }
    @PostMapping
    public ResultUtils saveEditProductAttribute(@RequestBody ProductAttribute productAttribute){
        productAttributeService.saveEditProductAttribute(productAttribute);
        return ResultUtils.success();
    }
    @GetMapping("/{id}")
    public ResultUtils queryAttributeById(@PathVariable("id") Long id){
        return ResultUtils.success(productAttributeService.getById(id));
    }
    @DeleteMapping("/{id}")
    public ResultUtils deleteAttributeById(@PathVariable("id") Long id){
        productAttributeService.removeById(id);
        return ResultUtils.success();
    }

    @GetMapping("/{pid}/{type}")
    public ResultUtils findAttributeList(@PathVariable("pid") Long pid,@PathVariable("type") Integer type){
        QueryWrapper<ProductAttribute> queryWrapper=new QueryWrapper<ProductAttribute>();
        queryWrapper.eq("product_attribute_category_id",pid);
        queryWrapper.eq("input_type",type);
        List<ProductAttribute> list=productAttributeService.list(queryWrapper);
        return ResultUtils.success(list);
    }
}
