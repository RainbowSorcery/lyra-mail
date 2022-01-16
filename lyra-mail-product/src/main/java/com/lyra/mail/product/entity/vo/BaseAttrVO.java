package com.lyra.mail.product.entity.vo;

public class BaseAttrVO {
    private Long attrId;
    private String attrValues;
    private Integer showDesc;

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getAttrValues() {
        return attrValues;
    }

    public void setAttrValues(String attrValues) {
        this.attrValues = attrValues;
    }

    public Integer getShowDesc() {
        return showDesc;
    }

    public void setShowDesc(Integer showDesc) {
        this.showDesc = showDesc;
    }
}
