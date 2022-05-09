package com.lyra.mail.product.entity.vo;

import com.lyra.mail.product.entity.PmsSkuImages;
import com.lyra.mail.product.entity.PmsSkuInfo;
import com.lyra.mail.product.entity.PmsSpuInfoDesc;

import java.util.List;

public class ItemVO {
    private PmsSkuInfo skuInfo;
    private List<PmsSkuImages> skuImages;
    private PmsSpuInfoDesc spuInfoDesc;
    private List<skuItemSaleAttrVO> saleAttr;
    private List<SpuItemAttrVO> spuItemAttrs;

    private Boolean hasStock = true;

    public Boolean getHasStock() {
        return hasStock;
    }

    public void setHasStock(Boolean hasStock) {
        this.hasStock = hasStock;
    }

    public static class SpuItemAttrVO {
        private String groupName;
        private List<SpuItemBaseAttr> spuItemBaseAttrs;

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public List<SpuItemBaseAttr> getSpuItemBaseAttrs() {
            return spuItemBaseAttrs;
        }

        public void setSpuItemBaseAttrs(List<SpuItemBaseAttr> spuItemBaseAttrs) {
            this.spuItemBaseAttrs = spuItemBaseAttrs;
        }
    }

    public static class SpuItemBaseAttr {
        private String attrName;
        private String attrValue;

        public String getAttrName() {
            return attrName;
        }

        public void setAttrName(String attrName) {
            this.attrName = attrName;
        }

        public String getAttrValue() {
            return attrValue;
        }

        public void setAttrValue(String attrValue) {
            this.attrValue = attrValue;
        }
    }

    public static class skuItemSaleAttrVO {
        private Long attrId;
        private String attrName;
        private List<String> attrValue;

        public Long getAttrId() {
            return attrId;
        }

        public void setAttrId(Long attrId) {
            this.attrId = attrId;
        }

        public String getAttrName() {
            return attrName;
        }

        public void setAttrName(String attrName) {
            this.attrName = attrName;
        }

        public List<String> getAttrValue() {
            return attrValue;
        }

        public void setAttrValue(List<String> attrValue) {
            this.attrValue = attrValue;
        }
    }

    public PmsSkuInfo getSkuInfo() {
        return skuInfo;
    }

    public void setSkuInfo(PmsSkuInfo skuInfo) {
        this.skuInfo = skuInfo;
    }

    public List<PmsSkuImages> getSkuImages() {
        return skuImages;
    }

    public void setSkuImages(List<PmsSkuImages> skuImages) {
        this.skuImages = skuImages;
    }

    public PmsSpuInfoDesc getSpuInfoDesc() {
        return spuInfoDesc;
    }

    public void setSpuInfoDesc(PmsSpuInfoDesc spuInfoDesc) {
        this.spuInfoDesc = spuInfoDesc;
    }

    public List<skuItemSaleAttrVO> getSaleAttr() {
        return saleAttr;
    }

    public void setSaleAttr(List<skuItemSaleAttrVO> saleAttr) {
        this.saleAttr = saleAttr;
    }

    public List<SpuItemAttrVO> getSpuItemAttrs() {
        return spuItemAttrs;
    }

    public void setSpuItemAttrs(List<SpuItemAttrVO> spuItemAttrs) {
        this.spuItemAttrs = spuItemAttrs;
    }
}
