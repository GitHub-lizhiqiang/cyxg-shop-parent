package com.fh.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.pms.entity.PmsBrand;
import com.fh.pms.service.IPmsBrandService;
import com.fh.pms.service.IProductCategoryService;
import com.fh.product.entity.*;
import com.fh.product.service.IPmsProductService;
import com.fh.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzq
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/pms/product")
public class PmsProductController {
    @Autowired
    private IPmsProductService productService;
    @Autowired
    private IProductCategoryService productCategoryService;
    @Autowired
    private IPmsBrandService brandService;


    @GetMapping
    public ResultUtils queryPageList(Page<PmsProduct> page){
        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<PmsProduct>();
        queryWrapper.notIn("delete_status",1);
        return ResultUtils.success(productService.page(page,queryWrapper));
    }
    @PostMapping
    public ResultUtils saveProduct(@RequestBody ProductUtils productUtils){
        productService.saveOrUpdateProduct(productUtils);
        return ResultUtils.success();
    }
    @DeleteMapping("{id}")
    public ResultUtils deleteProduct(@PathVariable("id") Long id){
        productService.removeById(id);
        return ResultUtils.success();
    }
    @GetMapping("{id}")
    public ResultUtils GetByIdProduct(@PathVariable("id") Long id){
        PmsProduct product= productService.getById(id);
        return ResultUtils.success(product);
    }
    @GetMapping("CategoryList")
    public ResultUtils GetCategoryList(){
        List<Map<String,Object>> list = productCategoryService.GetCategoryList();
        return ResultUtils.success(list);
    }
    @GetMapping("BrandList")
    public ResultUtils BrandList(){
        List<PmsBrand> list = brandService.list();
        return ResultUtils.success(list);
    }

}
