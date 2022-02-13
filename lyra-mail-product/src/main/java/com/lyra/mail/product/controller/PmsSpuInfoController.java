package com.lyra.mail.product.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.product.entity.PmsSpuInfo;
import com.lyra.mail.product.entity.vo.SpuVO;
import com.lyra.mail.product.service.IPmsSpuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * spu信息 前端控制器
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@RestController
@RequestMapping("/product/pms-spu-info")
public class PmsSpuInfoController {
    @Autowired
    private IPmsSpuInfoService spuInfoService;

    @PostMapping("/save")
    public Result save(@RequestBody SpuVO spuVO) {
        spuInfoService.saveSpuInfo(spuVO);

        return Result.ok();
    }

    @GetMapping("/list")
    public Result list(Integer current, Integer pageSize, String key, Long catelogId, Long brandId, Integer status) {
        if (current == null) {
            current = 0;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        Page<PmsSpuInfo> pmsSpuInfoPage = spuInfoService.SpuInfoListPage(current, pageSize, key, catelogId, brandId, status);

        return Result.ok(pmsSpuInfoPage);
    }

    @PostMapping("/{spuId}/up")
    public Result upProduct(@PathVariable Long spuId) {
        spuInfoService.upProduct(spuId);

        return Result.ok();
    }
}
