package com.lyra.mail.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.product.entity.PmsAttrGroup;
import com.lyra.mail.product.service.IPmsAttrGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 属性分组 前端控制器
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@RestController
@RequestMapping("/product/pms-attr-group")
public class PmsAttrGroupController {
    @Autowired
    private IPmsAttrGroupService attrGroupService;

    @GetMapping("/list/{categoryId}")
    public Result listPageByCategoryId(@PathVariable Long categoryId, Integer current, Integer pageSize) {
        if (categoryId == null) {
            categoryId = 0L;
        }

        if (current == null) {
            current = 0;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        IPage<PmsAttrGroup> pmsAttrGroupIPage = new Page<>(current, pageSize);
        QueryWrapper<PmsAttrGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("catelog_id", categoryId);
        attrGroupService.page(pmsAttrGroupIPage, queryWrapper);

        return Result.ok(pmsAttrGroupIPage);
    }

    @GetMapping("/conditionList/{categoryId}")
    public Result conditionList(@PathVariable String categoryId, Integer current, Integer pageSize, String keyWord) {
        if (keyWord == null) {
            keyWord = "";
        }
        IPage<PmsAttrGroup> pmsAttrGroupIPage = new Page<>(current, pageSize);
        QueryWrapper<PmsAttrGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("catelog_id", categoryId);
        String finalKeyWord = keyWord;
        queryWrapper.and((obj) -> {
            obj.eq("attr_group_id", finalKeyWord);
            obj.or().like("attr_group_name", finalKeyWord);
        });

        attrGroupService.page(pmsAttrGroupIPage, queryWrapper);

        return Result.ok(pmsAttrGroupIPage);
    }

    @PostMapping("/addAttrGroup")
    public Result addAttrGroup(@RequestBody PmsAttrGroup pmsAttrGroup) {
        if (pmsAttrGroup == null) {
            return Result.error();
        }

        attrGroupService.save(pmsAttrGroup);

        return Result.ok();
    }
}
