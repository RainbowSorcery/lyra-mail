package com.lyra.mail.product.entity.vo;

import java.util.List;

public class SpuItemAttrVO {
    private String groupName;
    private List<ItemVO.SpuItemBaseAttr> spuItemBaseAttrs;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<ItemVO.SpuItemBaseAttr> getSpuItemBaseAttrs() {
        return spuItemBaseAttrs;
    }

    public void setSpuItemBaseAttrs(List<ItemVO.SpuItemBaseAttr> spuItemBaseAttrs) {
        this.spuItemBaseAttrs = spuItemBaseAttrs;
    }
}
