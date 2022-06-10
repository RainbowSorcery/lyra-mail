package com.lyra.mail.auth.feign;

import com.lyra.mail.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("third-party-service")
public interface SendEmailFeign {
    @GetMapping("/sendEmail")
    public Result sendEmail(@RequestParam String email,@RequestParam String code);
}
