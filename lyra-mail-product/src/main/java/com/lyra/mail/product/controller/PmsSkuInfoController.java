package com.lyra.mail.product.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.product.entity.PmsSkuInfo;
import com.lyra.mail.product.service.IPmsSkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * sku信息 前端控制器
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@RestController
@RequestMapping("/product/pms-sku-info")
public class PmsSkuInfoController {
    @Autowired
    private IPmsSkuInfoService skuInfoService;

    @GetMapping("/list")
    public Result list(Integer current, Integer pageSize,
                       Long catelogId, Long brandId, Integer min, Integer max, String key) {
        if (current == null) {
            current = 0;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        IPage<PmsSkuInfo> skuInfoIPage = skuInfoService.skuPageList(current, pageSize, catelogId, brandId, min, max, key);

        return Result.ok(skuInfoIPage);
    }

}
