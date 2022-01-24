package com.lyra.mail.ware.entity.vo;

import java.util.List;

public class PurchaseDoneVO {
    private Long id;
    private List<PurchaseDoneItemVO> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PurchaseDoneItemVO> getItems() {
        return items;
    }

    public void setItems(List<PurchaseDoneItemVO> items) {
        this.items = items;
    }
}
