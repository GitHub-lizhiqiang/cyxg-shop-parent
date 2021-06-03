package com.fh.pms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.pms.entity.ProductCategory;
import com.fh.pms.service.IProductCategoryService;
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
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/pms/productcategory")
public class ProductCategoryController {
    @Autowired
    private IProductCategoryService productCategoryService;
    @GetMapping
    public ResultUtils queryProductCategoryList(Page<ProductCategory> page,Long parentId){
        QueryWrapper<ProductCategory> queryWrapper=new QueryWrapper();
        queryWrapper.eq("parent_id",parentId);
        productCategoryService.page(page,queryWrapper);
        return ResultUtils.success(page);
    }
    @GetMapping("List")
    public ResultUtils queryProductList(){
        QueryWrapper<ProductCategory> queryWrapper=new QueryWrapper();
        Long parentId=0l;
        queryWrapper.eq("parent_id",parentId);
        List<ProductCategory> list = productCategoryService.list(queryWrapper);
        return ResultUtils.success(list);
    }
    @PostMapping
    public ResultUtils saveProductCategory(@RequestBody ProductCategory productCategory){
        productCategoryService.saveProductCategory(productCategory);
        return ResultUtils.success();
    }
    @DeleteMapping("/{id}")
    public ResultUtils deleteProductCategory(@PathVariable("id") Long id){
        productCategoryService.removeById(id);
        return ResultUtils.success();
    }
    @PostMapping("/{id}")
    public ResultUtils UpswitchStatus(@PathVariable("id") Long id){
        ProductCategory product =productCategoryService.getById(id);
        productCategoryService.UpswitchStatus(product);
        return ResultUtils.success(product);
    }
    @RequestMapping("ShowStatus/{id}")
    public ResultUtils UpswitchShowStatus(@PathVariable("id") Long id){
        ProductCategory product =productCategoryService.getById(id);
        productCategoryService.UpswitchShowStatus(product);
        return ResultUtils.success(product);
    }
}
