package com.fh.pms.service;

import com.fh.pms.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzq
 * @since 2021-05-23
 */
public interface IProductCategoryService extends IService<ProductCategory> {

    void saveProductCategory(ProductCategory productCategory);

    ProductCategory UpswitchStatus(ProductCategory productCategory);

    ProductCategory UpswitchShowStatus(ProductCategory product);

    List<Map<String,Object>> GetCategoryList();
}
