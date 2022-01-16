package com.lyra.mail.product.entity.vo;

import java.math.BigDecimal;
import java.util.List;

public class SpuVO {
    private String spuName;
    private String spuDescription;
    private Long catalogId;
    private Long brandId;
    private BigDecimal weight;
    private Integer publishStatus;
    private List<String> decript;
    private List<String> images;
    private BoundVO bounds;
    private List<BaseAttrVO> baseAttrs;
    private List<SkuVO> skus;

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSpuDescription() {
        return spuDescription;
    }

    public void setSpuDescription(String spuDescription) {
        this.spuDescription = spuDescription;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public List<String> getDecript() {
        return decript;
    }

    public void setDecript(List<String> decript) {
        this.decript = decript;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public BoundVO getBounds() {
        return bounds;
    }

    public void setBounds(BoundVO bounds) {
        this.bounds = bounds;
    }

    public List<BaseAttrVO> getBaseAttrs() {
        return baseAttrs;
    }

    public void setBaseAttrs(List<BaseAttrVO> baseAttrs) {
        this.baseAttrs = baseAttrs;
    }

    public List<SkuVO> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuVO> skus) {
        this.skus = skus;
    }
}
