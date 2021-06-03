package com.fh.pms.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author haoxin
 * @since 2021-05-21
 */
@Data

public class ProductAttributeBo extends Page implements Serializable {

   private Long pid;

   private Long type;

}
