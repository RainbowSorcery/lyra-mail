package com.lyra.mail.common.enums;

public enum AttrType {
    baseType(1, "基本属性"),
    saleType(0, "销售属性") ;
    private Integer type;
    private String message;

    AttrType(Integer type, String message) {
        this.type = type;
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
