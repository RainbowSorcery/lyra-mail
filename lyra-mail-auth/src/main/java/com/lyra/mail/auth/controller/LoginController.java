package com.lyra.mail.auth.controller;

import com.alibaba.nacos.common.utils.StringUtils;
import com.lyra.mail.auth.constant.SendEmailConstant;
import com.lyra.mail.auth.constant.SendEmailEnums;
import com.lyra.mail.auth.feign.SendEmailFeign;
import com.lyra.mail.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LoginController {
    private SendEmailFeign sendEmailFeign;

    private StringRedisTemplate redisTemplate;

    @GetMapping("/sendEmail")
    public Result sendEmail(String email) {
        String emailCodeCache = redisTemplate.opsForValue().get(SendEmailConstant.EMAIL_CODE_CACHE_PREFIX + email);

        if (StringUtils.isNotBlank(emailCodeCache)) {
            long cacheCodeTime = Long.parseLong(Objects.requireNonNull(emailCodeCache).split("_")[1]);

            long l = System.currentTimeMillis() - cacheCodeTime;

            if (l < 60000) {
                return new Result(SendEmailEnums.SEND_EMAIL_EXCESSIVE_FREQUENCY.getCode(), SendEmailEnums.SEND_EMAIL_EXCESSIVE_FREQUENCY.getMessage(), SendEmailEnums.SEND_EMAIL_EXCESSIVE_FREQUENCY.getSuccess());
            }
        }

        String code = (int) (Math.random() * 100000) + "";
        redisTemplate.opsForValue().set(SendEmailConstant.EMAIL_CODE_CACHE_PREFIX + email, code + "_" + System.currentTimeMillis(), 20, TimeUnit.MINUTES);

        sendEmailFeign.sendEmail(email, code);

        return new Result(SendEmailEnums.SEND_EMAIL_SUCCESS.getCode(), SendEmailEnums.SEND_EMAIL_SUCCESS.getMessage(), SendEmailEnums.SEND_EMAIL_SUCCESS.getSuccess());
    }
}
