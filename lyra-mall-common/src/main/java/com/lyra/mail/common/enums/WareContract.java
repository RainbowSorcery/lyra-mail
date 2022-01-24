package com.lyra.mail.common.enums;

public class WareContract {
    public enum PurchaseStatusEnum {
        CREATE(0, "新建"),
        ASSIGNED(1, "已分配"),
        PURCHASING(2, "正在采购"),
        DONE(3, "已完成"),
        HAS_ERROR(4, "有异常");
        private Integer code;
        private String message;

        PurchaseStatusEnum(Integer code, String message) {
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

    public enum PurchaseDetailStatusEnum {
        CREATE(0, "新建"),
        ASSIGNED(1, "已分配"),
        PURCHASING(2, "正在采购"),
        DONE(3, "已完成"),
        HAS_ERROR(4, "有异常");
        ;
        private Integer code;
        private String message;

        PurchaseDetailStatusEnum(Integer code, String message) {
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
