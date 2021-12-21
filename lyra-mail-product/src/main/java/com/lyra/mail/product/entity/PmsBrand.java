package com.lyra.mail.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lyra.mail.common.valid.AddValid;
import com.lyra.mail.common.valid.UpdateValid;
import com.lyra.mail.common.valid.ValueList;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * <p>
 * 品牌
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@TableName("pms_brand")
public class PmsBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @TableId(value = "brand_id", type = IdType.AUTO)
    @NotNull(message = "修改时 品牌id不能为空", groups = {UpdateValid.class})
    @Null(message = "添加时 品牌id必须为空", groups = {AddValid.class})
    private Long brandId;

    /**
     * 品牌名
     */
    @NotBlank(message = "品牌名称不能为空", groups = {AddValid.class})
    private String name;

    /**
     * 品牌logo地址
     */
    @NotBlank(message = "logo不能为空", groups = {AddValid.class})
    @URL(message = "logo必须为合法url", groups = {AddValid.class, UpdateValid.class})
    private String logo;

    /**
     * 介绍
     */
    @NotBlank(message = "规格简介不能为空", groups = AddValid.class)
    private String descript;

    /**
     * 显示状态[0-不显示；1-显示]
     */
    @ValueList(values = {1, 0}, groups = {AddValid.class})
    private Integer showStatus;

    /**
     * 检索首字母
     */
    @NotBlank(message = "首字母不能为空", groups = {AddValid.class})
    @Pattern(regexp = "^[A-Za-z]{1}$",message = "请输入合法首字母", groups = {AddValid.class, UpdateValid.class})
    private String firstLetter;

    /**
     * 排序
     */
    @Min(value = 0, message = "排序必须大于1", groups = {AddValid.class, UpdateValid.class})
    private Integer sort;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }
    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "PmsBrand{" +
            "brandId=" + brandId +
            ", name=" + name +
            ", logo=" + logo +
            ", descript=" + descript +
            ", showStatus=" + showStatus +
            ", firstLetter=" + firstLetter +
            ", sort=" + sort +
        "}";
    }
}
