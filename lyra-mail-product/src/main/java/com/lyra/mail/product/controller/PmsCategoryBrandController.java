package com.lyra.mail.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.product.entity.PmsCategoryBranRelation;
import com.lyra.mail.product.service.PmsCategoryBranRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/categoryBrand")
public class PmsCategoryBrandController {
    @Autowired
    private PmsCategoryBranRelationService pmsCategoryBranRelationService;

    @PostMapping("/categoryBrandRelation/save")
    public Result categoryBrandRelation(@RequestBody PmsCategoryBranRelation categoryBranRelation) {
        if (categoryBranRelation.getBrandId() == null || categoryBranRelation.getCatelogId() == null) {
            return Result.error();
        }

        pmsCategoryBranRelationService.categoryBrandRelation(categoryBranRelation);

        return Result.ok();
    }

    @GetMapping("categoryBrandRelation/list")
    public Result categoryBrandRelationList(Long brandId) {
        QueryWrapper<PmsCategoryBranRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id", brandId);
        List<PmsCategoryBranRelation> list = pmsCategoryBranRelationService.list(queryWrapper);

        return Result.ok(list);
    }

    @PostMapping("categoryBrandRelation/delete")
    public Result categoryBrandRelationDelete(Long id) {
        pmsCategoryBranRelationService.removeById(id);

    return Result.ok();
    }

}
