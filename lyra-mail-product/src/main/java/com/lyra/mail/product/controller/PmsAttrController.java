package com.lyra.mail.product.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.product.entity.vo.BaseListVO;
import com.lyra.mail.product.entity.vo.PmsAttrVO;
import com.lyra.mail.product.service.IPmsAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 商品属性 前端控制器
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@RestController
@RequestMapping("/product/pms-attr")
public class PmsAttrController {
    @Autowired
    private IPmsAttrService attrService;

    @PostMapping("/save")
    public Result save(@RequestBody PmsAttrVO attrVO) {
        attrService.saveAttrVo(attrVO);

        return Result.ok();
    }

    @GetMapping("/base/list/{categoryId}")
    public Result baseList(@PathVariable Long categoryId, Integer pageSize, Integer current, String keyword) {
        if (current == null) {
            current = 0;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        IPage<BaseListVO> baseListVOIPage = attrService.baseList(categoryId, pageSize, current, keyword);

        return Result.ok(baseListVOIPage);
    }
}
