package com.lyra.mail.ware.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.ware.entity.WmsWareSku;
import com.lyra.mail.ware.service.IWmsWareSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 商品库存 前端控制器
 * </p>
 *
 * @author lyra
 * @since 2022-01-16
 */
@RestController
@RequestMapping("/wms-ware-sku")
public class WmsWareSkuController {
    @Autowired
    private IWmsWareSkuService wmsWareSkuService;

    @GetMapping("/list")
    public Result list(Integer current, Integer pageSize, Long skuId, Long wareId) {
        if (current == null) {
            current = 0;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        IPage<WmsWareSku> wmsWareSkuIPage =  wmsWareSkuService.findWareSkuList(current, pageSize, skuId, wareId);

        return Result.ok(wmsWareSkuIPage);
    }

    @PostMapping("/save")
    public Result save(@RequestBody WmsWareSku wmsWareSku) {
        wmsWareSkuService.save(wmsWareSku);

        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WmsWareSku wmsWareSku) {
        wmsWareSkuService.updateById(wmsWareSku);

        return Result.ok();
    }

    @GetMapping("/info/{skuId}")
    public Result info(@PathVariable Long skuId) {
        WmsWareSku wareSku = wmsWareSkuService.getById(skuId);

        return Result.ok(wareSku);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody List<Long> ids) {
        wmsWareSkuService.removeByIds(ids);

        return Result.ok();
    }
}
