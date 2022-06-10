package com.lyra.mail.third.controller;

import com.lyra.mail.common.result.Result;
import com.lyra.mail.third.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendEmailController {
    @Autowired
    private IMailService mailService;

    @GetMapping("/sendEmail")
    public Result sendEmail(String email, String code) {
        mailService.sendMail(email, code);
        return Result.ok();
    }
}
