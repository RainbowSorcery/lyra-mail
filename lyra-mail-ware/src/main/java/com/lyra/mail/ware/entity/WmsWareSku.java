package com.lyra.mail.ware.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商品库存
 * </p>
 *
 * @author lyra
 * @since 2022-01-16
 */
@TableName("wms_ware_sku")
public class WmsWareSku implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * 仓库id
     */
    private Long wareId;

    /**
     * 库存数
     */
    private Integer stock;

    /**
     * sku_name
     */
    private String skuName;

    /**
     * 锁定库存
     */
    private Integer stockLocked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }
    public Integer getStockLocked() {
        return stockLocked;
    }

    public void setStockLocked(Integer stockLocked) {
        this.stockLocked = stockLocked;
    }

    @Override
    public String toString() {
        return "WmsWareSku{" +
            "id=" + id +
            ", skuId=" + skuId +
            ", wareId=" + wareId +
            ", stock=" + stock +
            ", skuName=" + skuName +
            ", stockLocked=" + stockLocked +
        "}";
    }
}
