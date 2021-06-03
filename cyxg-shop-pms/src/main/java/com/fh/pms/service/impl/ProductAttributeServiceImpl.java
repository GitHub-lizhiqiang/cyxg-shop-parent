package com.fh.pms.service.impl;

import com.fh.pms.entity.ProductAttribute;
import com.fh.pms.mapper.ProductAttributeMapper;
import com.fh.pms.service.IProductAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzq
 * @since 2021-05-22
 */
@Service
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements IProductAttributeService {

    @Override
    public void saveEditProductAttribute(ProductAttribute productAttribute) {
        super.saveOrUpdate(productAttribute);
    }
}
