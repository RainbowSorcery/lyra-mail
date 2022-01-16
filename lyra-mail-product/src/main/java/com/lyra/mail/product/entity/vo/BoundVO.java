package com.lyra.mail.product.entity.vo;

import java.math.BigDecimal;

public class BoundVO {
    private BigDecimal buyBounds;
    private BigDecimal growBounds;

    public BigDecimal getBuyBounds() {
        return buyBounds;
    }

    public void setBuyBounds(BigDecimal buyBounds) {
        this.buyBounds = buyBounds;
    }

    public BigDecimal getGrowBounds() {
        return growBounds;
    }

    public void setGrowBounds(BigDecimal growBounds) {
        this.growBounds = growBounds;
    }
}
