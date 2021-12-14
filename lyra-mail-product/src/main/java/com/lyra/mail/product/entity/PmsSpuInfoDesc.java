package com.lyra.mail.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * spu信息介绍
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@TableName("pms_spu_info_desc")
public class PmsSpuInfoDesc implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId
    private Long spuId;

    /**
     * 商品介绍
     */
    private String decript;

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }
    public String getDecript() {
        return decript;
    }

    public void setDecript(String decript) {
        this.decript = decript;
    }

    @Override
    public String toString() {
        return "PmsSpuInfoDesc{" +
            "spuId=" + spuId +
            ", decript=" + decript +
        "}";
    }
}
