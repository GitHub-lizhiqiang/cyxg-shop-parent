package com.fh.pms.service;

import com.fh.pms.entity.PmsBrand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.result.ResultUtils;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzq
 * @since 2021-05-22
 */
public interface IPmsBrandService extends IService<PmsBrand> {

    ResultUtils saveOrupdateBrand(PmsBrand pmsBrand);

    ResultUtils getBrandById(Long brandId);

    ResultUtils deleteBrandById(Long brandId);
}
