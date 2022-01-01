package com.lyra.mail.common.exception;

import com.lyra.mail.common.result.ResponseStatusEnum;
import com.lyra.mail.common.result.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class LyraMailExceptionControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result validExceptionHandle(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        Map<String, Object> errorMap = new HashMap<>();
        fieldErrors.forEach((fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }));

        return new Result(ResponseStatusEnum.CHECK_FILED.getCode(), ResponseStatusEnum.CHECK_FILED.getMessage(), false, errorMap);
    }

    @ExceptionHandler(LyraMailException.class)
    public Result lyraMailException(LyraMailException e) {
        return new Result(ResponseStatusEnum.FAILED.getCode(), ResponseStatusEnum.FAILED.getMessage(), false, e.getMessage());
    }
}
