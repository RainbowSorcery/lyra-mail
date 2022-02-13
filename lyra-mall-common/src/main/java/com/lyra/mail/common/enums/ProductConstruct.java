package com.lyra.mail.common.enums;

public class ProductConstruct {
    public enum SpuStatus {
        NEW_CONSTRUCTION_STATUS(0, "新建"),
        UP_STATUS(1, "上架"),
        DOWN_STATUS(2, "下架");
        private Integer code;
        private String message;

        SpuStatus(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
