package com.lyra.mail.ware.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 库存工作单
 * </p>
 *
 * @author lyra
 * @since 2022-01-16
 */
@TableName("wms_ware_order_task_detail")
public class WmsWareOrderTaskDetail implements Serializable {

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
     * sku_name
     */
    private String skuName;

    /**
     * 购买个数
     */
    private Integer skuNum;

    /**
     * 工作单id
     */
    private Long taskId;

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
    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }
    public Integer getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(Integer skuNum) {
        this.skuNum = skuNum;
    }
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "WmsWareOrderTaskDetail{" +
            "id=" + id +
            ", skuId=" + skuId +
            ", skuName=" + skuName +
            ", skuNum=" + skuNum +
            ", taskId=" + taskId +
        "}";
    }
}
