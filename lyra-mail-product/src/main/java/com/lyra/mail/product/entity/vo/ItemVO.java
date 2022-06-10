package com.lyra.mail.product.entity.vo;

import com.lyra.mail.product.entity.PmsSkuImages;
import com.lyra.mail.product.entity.PmsSkuInfo;
import com.lyra.mail.product.entity.PmsSpuInfoDesc;

import java.util.List;

public class ItemVO {
    // 商品信息
    private PmsSkuInfo skuInfo;
    // 商品图片
    private List<PmsSkuImages> skuImages;
    // 商品介绍图
    private PmsSpuInfoDesc spuInfoDesc;
    // 销售属性
    private List<skuItemSaleAttrVO> saleAttr;
    // 商品规格
    private List<SpuItemAttrVO> spuItemAttrs;

    private Boolean hasStock = true;

    public Boolean getHasStock() {
        return hasStock;
    }

    public void setHasStock(Boolean hasStock) {
        this.hasStock = hasStock;
    }

    public static class SpuItemAttrVO {
        // 属性组名称 如主体
        private String groupName;
        // 组下的不同属性 如品牌 型号等
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
        // 属性名称
        private String attrName;
        // 属性值
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
        //销售属性id
        private Long attrId;
        // 销售属性名称
        private String attrName;
        // 销售属性值 因为销售属性是一对多的 比如一个颜色分类下有多个不同的颜色, 比如销售版本有128GB 64GB
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
