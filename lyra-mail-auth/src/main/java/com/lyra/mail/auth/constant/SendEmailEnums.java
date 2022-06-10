package com.lyra.mail.auth.constant;

public enum SendEmailEnums {
    SEND_EMAIL_EXCESSIVE_FREQUENCY(500, "发送频率过高", false),
    SEND_EMAIL_SUCCESS(200, "发送邮件成功" ,true),
    SEND_EMAIL_CHECK_FAILED(501, "验证码校验失败.", false);
    private Integer code;
    private String message;
    private Boolean success;

    SendEmailEnums(Integer code, String message, Boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
