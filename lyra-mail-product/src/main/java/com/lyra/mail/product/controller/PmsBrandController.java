package com.lyra.mail.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.common.valid.AddValid;
import com.lyra.mail.common.valid.UpdateValid;
import com.lyra.mail.product.entity.PmsBrand;
import com.lyra.mail.product.entity.PmsCategory;
import com.lyra.mail.product.service.IPmsBrandService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    public Result update(@RequestBody @Validated(value = {UpdateValid.class}) PmsBrand brand) {
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

        QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        IPage<PmsBrand> brandIPage = new Page<>(current, pageSize);
        brandService.page(brandIPage, queryWrapper);

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
    public Result addBrand(@RequestBody @Validated(value = {AddValid.class}) PmsBrand brand) {
        Map<String, Object> errorsMap = new HashMap<>();

        if (brand == null) {
            return Result.error();
        }

        brandService.save(brand);

        return Result.ok();
    }

    @GetMapping("searchBrandById")
    public Result searchBrandById(Long brandId) {
        PmsBrand brand = brandService.getById(brandId);

        return Result.ok(brand);
    }

    @PostMapping("/delete")
    public Result deleteBrandById(Long brandId) {
        if (brandId == null) {
            return Result.error();
        }

        brandService.removeById(brandId);

        return Result.ok();
    }

    @PostMapping("/deleteAllList")
    public Result deleteAllList(@RequestBody List<Long> brandList) {
        if (brandList == null) {
            return Result.error();
        }

        brandService.removeByIds(brandList);

        return Result.ok();
    }
}
