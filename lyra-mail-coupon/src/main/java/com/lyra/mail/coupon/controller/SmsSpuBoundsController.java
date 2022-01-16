package com.lyra.mail.coupon.controller;


import com.lyra.mail.common.result.Result;
import com.lyra.mail.common.to.BoundsTO;
import com.lyra.mail.coupon.entity.SmsSpuBounds;
import com.lyra.mail.coupon.service.ISmsSpuBoundsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品spu积分设置 前端控制器
 * </p>
 *
 * @author lyra
 * @since 2022-01-14
 */
@RestController
@RequestMapping("/sms-spu-bounds")
public class SmsSpuBoundsController {
    @Autowired
    private ISmsSpuBoundsService spuBoundsService;

    @PostMapping("/save")
    public Result save(@RequestBody BoundsTO boundsTO) {
        SmsSpuBounds smsSpuBounds = new SmsSpuBounds();
        BeanUtils.copyProperties(boundsTO, smsSpuBounds);
        spuBoundsService.save(smsSpuBounds);

        return Result.ok();
    }
}
