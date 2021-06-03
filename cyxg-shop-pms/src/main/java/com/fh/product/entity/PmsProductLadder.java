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
public class PmsProductLadder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 满足的商品数量
     */
    private Integer count;

    /**
     * 折扣
     */
    private BigDecimal discount;

    /**
     * 折后价格
     */
    private BigDecimal price;


}
