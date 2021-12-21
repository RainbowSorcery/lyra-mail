package com.lyra.mail.common.result;

public enum ResponseStatusEnum {
    SUCCESS(200, "成功!"),
    FAILED(500, "失败!"),
    CHECK_FILED(501, "参数校验失败");
    private Integer code;
    private String message;

    ResponseStatusEnum(Integer code, String message) {
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
