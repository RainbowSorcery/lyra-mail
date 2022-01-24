package com.lyra.mail.ware.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.ware.entity.WmsPurchase;
import com.lyra.mail.ware.entity.WmsPurchaseDetail;
import com.lyra.mail.ware.entity.vo.PurchaseMergeVO;
import com.lyra.mail.ware.service.IWmsPurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyra
 * @since 2022-01-22
 */
@RestController
@RequestMapping("/wms-purchase-detail")
public class WmsPurchaseDetailController {
    @Autowired
    private IWmsPurchaseDetailService purchaseDetailService;

    @GetMapping("/list")
    public Result list(Integer current, Integer pageSize, String key, Integer status, Long wareId) {
        if (current == null) {
            current = 0;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        IPage<WmsPurchaseDetail> purchaseIPage = purchaseDetailService.findPurchaseList(current, pageSize, key, status, wareId);

        return Result.ok(purchaseIPage);
    }

    @PostMapping("/save")
    public Result save(@RequestBody WmsPurchaseDetail purchaseDetail) {
        purchaseDetailService.save(purchaseDetail);

        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WmsPurchaseDetail purchaseDetail) {
        purchaseDetailService.updateById(purchaseDetail);

        return Result.ok();
    }

    @GetMapping("/info/{id}")
    public Result infoById(@PathVariable Long id) {
        WmsPurchaseDetail purchaseDetail = purchaseDetailService.getById(id);

        return Result.ok(purchaseDetail);
    }

    @PostMapping("/delete")
    public Result deleteIds(@RequestBody List<Long> ids) {
        purchaseDetailService.removeByIds(ids);

        return Result.ok();
    }

    @PostMapping("/merge")
    public Result merge(@RequestBody PurchaseMergeVO mergeVO) {
        purchaseDetailService.merge(mergeVO);

        return Result.ok();
    }
}
