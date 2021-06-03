package com.fh.roletoadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lzq
 * @since 2021-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsRoleadmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField(" userId")
    private Long  userid;

    /**
     * 角色ID
     */
    @TableField("roleId")
    private Long roleid;


}
