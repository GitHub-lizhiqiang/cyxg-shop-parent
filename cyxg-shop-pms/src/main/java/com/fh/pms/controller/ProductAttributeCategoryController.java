package com.fh.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.pms.entity.ProductAttributeCategory;
import com.fh.pms.service.IProductAttributeCategoryService;
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
@RequestMapping("/productAttributeCategorys")
public class ProductAttributeCategoryController {

    @Autowired
    private IProductAttributeCategoryService productAttributeCategoryService;

    @GetMapping
    public ResultUtils queryAttributeCategoryList(Page<ProductAttributeCategory> page){
        productAttributeCategoryService.page(page);
        return ResultUtils.success(page);
    }
    @PostMapping
    public ResultUtils saveOrUpdate(@RequestBody ProductAttributeCategory productAttributeCategory){
        productAttributeCategoryService.saveOrUpdate(productAttributeCategory);
        return ResultUtils.success();
    }
    @GetMapping("/{id}")
    public ResultUtils getById(@PathVariable("id") Long id){
        return ResultUtils.success(productAttributeCategoryService.getById(id));
    }
    @DeleteMapping("/{id}")
    public ResultUtils deleteAttributeCategory(@PathVariable("id") Long id){
        return ResultUtils.success(productAttributeCategoryService.removeById(id));
    }
    @GetMapping("/parent")
    public ResultUtils queryAttributeCategoryList(){
        List<ProductAttributeCategory> list = productAttributeCategoryService.list();
        return ResultUtils.success(list);
    }
    @GetMapping("List")
    public ResultUtils getAllAttributeCategoryList(){
       List<ProductAttributeCategory> list=productAttributeCategoryService.list();
        return ResultUtils.success(list);
    }

}
