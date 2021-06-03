package com.fh.pms.service.impl;

import com.fh.pms.entity.PmsBrand;
import com.fh.pms.mapper.PmsBrandMapper;
import com.fh.pms.service.IPmsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements IPmsBrandService {

    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public ResultUtils saveOrupdateBrand(PmsBrand pmsBrand) {
        super.saveOrUpdate(pmsBrand);
        return ResultUtils.success();
    }

    @Override
    public ResultUtils getBrandById(Long brandId) {
        return ResultUtils.success(brandMapper.selectById(brandId));
    }

    @Override
    public ResultUtils deleteBrandById(Long brandId) {
        brandMapper.deleteById(brandId);
        return ResultUtils.success();
    }
}
