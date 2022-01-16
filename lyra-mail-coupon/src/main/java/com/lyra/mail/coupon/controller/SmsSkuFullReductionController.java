package com.lyra.mail.coupon.controller;


import com.lyra.mail.common.result.Result;
import com.lyra.mail.common.to.SkuReductionTO;
import com.lyra.mail.coupon.mapper.SmsSkuFullReductionMapper;
import com.lyra.mail.coupon.service.ISmsSkuFullReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品满减信息 前端控制器
 * </p>
 *
 * @author lyra
 * @since 2022-01-14
 */
@RestController
@RequestMapping("/sms-sku-full-reduction")
public class SmsSkuFullReductionController {
    @Autowired
    private ISmsSkuFullReductionService smsSkuFullReductionService;

    @PostMapping("/saveSkuFullReduction")
    public Result saveSkuFullReduction(@RequestBody SkuReductionTO skuReductionTO) {
        smsSkuFullReductionService.saveSkuFullReduction(skuReductionTO);

        return Result.ok();
    }
}
