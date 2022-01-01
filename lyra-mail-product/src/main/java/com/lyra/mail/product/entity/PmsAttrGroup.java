package com.lyra.mail.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lyra.mail.common.valid.AddValid;
import com.lyra.mail.common.valid.UpdateValid;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 属性分组
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@TableName("pms_attr_group")
public class PmsAttrGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分组id
     */
    @TableId(value = "attr_group_id", type = IdType.AUTO)
    @NotNull(message = "修改时 attrGroupId不能为空", groups = {UpdateValid.class})
    @Null(message = "添加时 attrGroupId必须为空", groups = {AddValid.class})
    private Long attrGroupId;

    /**
     * 组名
     */
    @NotBlank(message = "attrGroupId名称不能为空", groups = {AddValid.class})
    private String attrGroupName;

    /**
     * 排序
     */
    @Min(value = 0, message = "排序必须大于1", groups = {AddValid.class, UpdateValid.class})
    private Integer sort;

    /**
     * 描述
     */
    @NotBlank(message = "简介不能为空", groups = {AddValid.class})
    private String descript;

    /**
     * 组图标
     */
    @NotBlank(message = "icon不能为空", groups = {AddValid.class})
    @URL(message = "icon必须为合法url", groups = {AddValid.class, UpdateValid.class})
    private String icon;

    /**
     * 所属分类id
     */
    @Min(value = 0, message = "categoryId必须为合法数字", groups = {AddValid.class, UpdateValid.class})
    private Long catelogId;

    public List<Long> getAttrGroupPath() {
        return attrGroupPath;
    }

    public void setAttrGroupPath(List<Long> attrGroupPath) {
        this.attrGroupPath = attrGroupPath;
    }

    @TableField(exist = false)
    private List<Long> attrGroupPath;



    public Long getAttrGroupId() {
        return attrGroupId;
    }

    public void setAttrGroupId(Long attrGroupId) {
        this.attrGroupId = attrGroupId;
    }
    public String getAttrGroupName() {
        return attrGroupName;
    }

    public void setAttrGroupName(String attrGroupName) {
        this.attrGroupName = attrGroupName;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Long getCatelogId() {
        return catelogId;
    }

    public void setCatelogId(Long catelogId) {
        this.catelogId = catelogId;
    }

    @Override
    public String toString() {
        return "PmsAttrGroup{" +
            "attrGroupId=" + attrGroupId +
            ", attrGroupName=" + attrGroupName +
            ", sort=" + sort +
            ", descript=" + descript +
            ", icon=" + icon +
            ", catelogId=" + catelogId +
        "}";
    }
}
