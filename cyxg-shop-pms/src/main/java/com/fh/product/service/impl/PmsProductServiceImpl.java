package com.fh.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.product.entity.*;
import com.fh.product.mapper.PmsProductMapper;
import com.fh.product.service.IPmsMemberPriceService;
import com.fh.product.service.IPmsProductFullReductionService;
import com.fh.product.service.IPmsProductLadderService;
import com.fh.product.service.IPmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzq
 * @since 2021-05-23
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {
    @Autowired
    private IPmsMemberPriceService pmsMemberPriceService;
    @Autowired
    private IPmsProductLadderService pmsProductLadderService;
    @Autowired
    private IPmsProductFullReductionService pmsProductFullReductionService;
    @Override
    @Transactional
    public void saveOrUpdateProduct(ProductUtils pmsProducts) {
       PmsProduct pmsProduct=pmsProducts.getPmsProducts();
        if(pmsProduct.getId()==null){
            pmsProduct.setCreateTime(new Date());
        }
        pmsProduct.setUpdateTime(new Date());
        saveOrUpdate(pmsProduct);

        SaveLadders(pmsProducts,pmsProduct.getId());

        SaveReductionLists(pmsProducts,pmsProduct.getId());

        SaveMember(pmsProducts,pmsProduct.getId());
    }

    private void SaveMember(ProductUtils pmsProducts, Long productId) {
        QueryWrapper<PmsMemberPrice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        pmsMemberPriceService.remove(queryWrapper);
        List<PmsMemberPrice> list =pmsProducts.getPmsMemberPrices();
        list.forEach(Reduction->{
            Reduction.setProductId(productId);
        });
        pmsMemberPriceService.saveOrUpdateBatch(list);
    }

    private void SaveReductionLists(ProductUtils pmsProducts, Long productId) {
        QueryWrapper<PmsProductFullReduction> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        pmsProductFullReductionService.remove(queryWrapper);
        List<PmsProductFullReduction> list =pmsProducts.getProductReductionLists();
        list.forEach(Reduction->{
            Reduction.setProductId(productId);
        });
        pmsProductFullReductionService.saveOrUpdateBatch(list);
    }


    private void SaveLadders(ProductUtils pmsProducts,Long productId){
        QueryWrapper<PmsProductLadder> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        pmsProductLadderService.remove(queryWrapper);
        List<PmsProductLadder> list =pmsProducts.getProductLadderLists();
        list.forEach(Ladder->{
            Ladder.setProductId(productId);
        });
        pmsProductLadderService.saveOrUpdateBatch(list);
    }
}
