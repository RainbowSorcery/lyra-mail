package com.lyra.mail.product.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.product.entity.PmsBrand;
import com.lyra.mail.product.entity.PmsCategory;
import com.lyra.mail.product.service.IPmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 品牌 前端控制器
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@RestController
@RequestMapping("/product/pms-brand")
public class PmsBrandController {
    @Autowired
    private IPmsBrandService brandService;

    @PostMapping("/update")
    public Result update(@RequestBody PmsBrand brand) {
        brandService.updateById(brand);

        return Result.ok();
    }

    @GetMapping("/findAllBread")
    public Result findAllBread() {
        List<PmsBrand> brandList = brandService.list();

        return Result.ok(brandList);
    }

    @GetMapping("/findPage")
    public Result findPage(Integer current, Integer pageSize) {
        if (current == null) {
            current = 0;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        IPage<PmsBrand> brandIPage = new Page<>(current, pageSize);
        brandService.page(brandIPage);

        return Result.ok(brandIPage);
    }

    @GetMapping("/getBrandById")
    public Result findBrandById(Long brandId) {
        if (brandId == null) {
            return Result.error();
        }

        PmsBrand byId = brandService.getById(brandId);
        return Result.ok(byId);
    }

    @PostMapping("/addBrand")
    public Result addBrand(@RequestBody PmsBrand brand) {
        if (brand == null) {
            return Result.error();
        }

        brandService.save(brand);

        return Result.ok();
    }
}
