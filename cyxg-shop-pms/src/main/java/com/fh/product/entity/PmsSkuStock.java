package com.fh.product.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lzq
 * @since 2021-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsSkuStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * sku编码
     */
    private String skuCode;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 预警库存
     */
    private Integer lowStock;

    /**
     * 规格属性1
     */
    private String sp1;

    /**
     * 规格属性2
     */
    private String sp2;

    /**
     * 规格属性3
     */
    private String sp3;

    /**
     * 展示图片
     */
    private String pic;

    /**
     * 销量
     */
    private Integer sale;

    /**
     * 单品促销价格
     */
    private BigDecimal promotionPrice;

    /**
     * 锁定库存
     */
    private Integer lockStock;


}
