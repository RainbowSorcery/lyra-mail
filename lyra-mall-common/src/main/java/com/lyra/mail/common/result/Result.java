package com.lyra.mail.common.result;

import java.util.Map;

public class Result {
    private Integer statusCode;
    private String message;
    private Boolean success;
    private Object data;

    public static Result ok(Map<String, Object> data) {
        return new Result(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMessage(),true, data);
    }

    public static Result ok(Object data) {
        return new Result(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMessage(),true, data);
    }

    public static Result ok() {
        return new Result(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMessage(),true);
    }

    public static Result error() {
        return new Result(ResponseStatusEnum.FAILED.getCode(), ResponseStatusEnum.FAILED.getMessage(), false);
    }

    public static Result error(Object data) {
        return new Result(ResponseStatusEnum.FAILED.getCode(), ResponseStatusEnum.FAILED.getMessage(), false, data);
    }

    public Result(Integer statusCode, String message, Boolean success) {
        this.statusCode = statusCode;
        this.message = message;
        this.success = success;
    }

    public Result(Integer statusCode, String message, Boolean success, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
