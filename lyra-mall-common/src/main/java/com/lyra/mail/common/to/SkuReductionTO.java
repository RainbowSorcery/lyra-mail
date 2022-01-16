package com.lyra.mail.common.to;

import java.math.BigDecimal;
import java.util.List;

public class SkuReductionTO {
    private Long skuId;
    private Integer fullCount;
    private BigDecimal discount;
    private Integer countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private BigDecimal priceStatus;
    private List<MemberPrice> memberPrice;


    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getFullCount() {
        return fullCount;
    }

    public void setFullCount(Integer fullCount) {
        this.fullCount = fullCount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getCountStatus() {
        return countStatus;
    }

    public void setCountStatus(Integer countStatus) {
        this.countStatus = countStatus;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    public BigDecimal getReducePrice() {
        return reducePrice;
    }

    public void setReducePrice(BigDecimal reducePrice) {
        this.reducePrice = reducePrice;
    }

    public BigDecimal getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(BigDecimal priceStatus) {
        this.priceStatus = priceStatus;
    }

    public List<MemberPrice> getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(List<MemberPrice> memberPrice) {
        this.memberPrice = memberPrice;
    }
}
