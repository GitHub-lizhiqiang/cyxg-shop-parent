package com.fh.product.entity;

import lombok.Data;

import java.util.List;

@Data
public class ProductUtils{
    PmsProduct pmsProducts;
    List<PmsProductFullReduction> productReductionLists;
    List<PmsProductLadder> productLadderLists;
    List<PmsMemberPrice> pmsMemberPrices;
    List<PmsSkuStock> skuStockLists;
    List<PmsProductAttributeValue> selectProductParamsLists;
    List<String> productPicLists;
}
