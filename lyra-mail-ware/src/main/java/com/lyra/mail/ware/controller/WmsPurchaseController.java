package com.lyra.mail.ware.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.ware.entity.WmsPurchase;
import com.lyra.mail.ware.entity.WmsPurchaseDetail;
import com.lyra.mail.ware.entity.vo.PurchaseDoneVO;
import com.lyra.mail.ware.service.IWmsPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 采购信息 前端控制器
 * </p>
 *
 * @author lyra
 * @since 2022-01-16
 */
@RestController
@RequestMapping("/wms-purchase")
public class WmsPurchaseController {
    @Autowired
    private IWmsPurchaseService purchaseService;

    @GetMapping("/list")
    public Result list(Integer current, Integer pageSize, String key, Integer status) {
        if (current == null) {
            current = 0;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        IPage<WmsPurchase> purchaseIPage = purchaseService.findPurchaseList(current, pageSize, key, status);

        return Result.ok(purchaseIPage);
    }

    @GetMapping("/info/{id}")
    public Result purchaseInfo(@PathVariable Long id) {
        WmsPurchase purchase = purchaseService.getById(id);

        return Result.ok(purchase);
    }

    @PostMapping("/save")
    public Result save(@RequestBody WmsPurchase purchase) {
        purchaseService.save(purchase);

        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody WmsPurchase purchase) {
        purchaseService.updateById(purchase);

        return Result.ok();
    }

    @GetMapping("/undeceive/list")
    public Result undeceiveList() {
        List<WmsPurchase> purchases = purchaseService.undeceiveList();

        return Result.ok(purchases);
    }

    @PostMapping("/received")
    public Result received(@RequestBody List<Long> ids) {
        purchaseService.received(ids);

        return Result.ok();
    }

    @PostMapping("/done")
    public Result done(@RequestBody PurchaseDoneVO purchaseDoneVO) {
        purchaseService.done(purchaseDoneVO);

        return Result.ok();
    }

    @PostMapping("/delete")
    public Result deleteIds(@RequestBody List<Long> ids) {
        purchaseService.removeByIds(ids);

        return Result.ok();
    }

}
