package com.fh.pms.service;

import com.fh.pms.entity.ProductAttribute;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzq
 * @since 2021-05-22
 */
public interface IProductAttributeService extends IService<ProductAttribute> {

    void saveEditProductAttribute(ProductAttribute productAttribute);
}
