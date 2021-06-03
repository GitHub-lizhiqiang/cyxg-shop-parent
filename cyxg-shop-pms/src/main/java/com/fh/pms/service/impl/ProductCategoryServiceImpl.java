package com.fh.pms.service.impl;

import com.fh.pms.entity.ProductCategory;
import com.fh.pms.mapper.ProductCategoryMapper;
import com.fh.pms.service.IProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzq
 * @since 2021-05-23
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {

    @Override
    public void saveProductCategory(ProductCategory productCategory) {
        if(productCategory.getParentId()==0){
            productCategory.setLevel(0);
        }else {
            productCategory.setLevel(1);
        }
        saveOrUpdate(productCategory);
    }

    @Override
    @Transactional
    public ProductCategory UpswitchStatus(ProductCategory productCategory) {
        if(productCategory.getNavStatus()==1){
            productCategory.setNavStatus(0);
        }else if(productCategory.getNavStatus()==0){
            productCategory.setNavStatus(1);
        }
         updateById(productCategory);
        return  productCategory;
    }

    @Override
    public ProductCategory UpswitchShowStatus(ProductCategory product) {
        if(product.getShowStatus()==0){
            product.setShowStatus(1);
        }else if(product.getShowStatus()==1){
            product.setShowStatus(0);
        }
        updateById(product);
        return product;
    }

    @Override
    public List<Map<String,Object>> GetCategoryList() {
        List<ProductCategory> list = list(null);
        return getTree(list,0l);
    }

    private List<Map<String,Object>> getTree(List<ProductCategory> list,Long parentid) {
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
       list.forEach(Prod -> {
            Map<String, Object> map = null;
            if(parentid.equals(Prod.getParentId())) {
                map = new HashMap<String, Object>();
                map.put("id", Prod.getId());
                map.put("label", Prod.getName());
                map.put("value", Prod.getParentId());
                map.put("children", getTree(list, Prod.getId()));
            }
                if (map != null) {
                    listMap.add(map);
                }
        });
        return listMap;
    }

}
