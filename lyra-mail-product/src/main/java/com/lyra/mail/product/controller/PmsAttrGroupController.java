package com.lyra.mail.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.product.entity.PmsAttr;
import com.lyra.mail.product.entity.PmsAttrGroup;
import com.lyra.mail.product.entity.vo.AttrGroupRelationVO;
import com.lyra.mail.product.entity.vo.AttrGroupWithAttr;
import com.lyra.mail.product.service.IPmsAttrGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

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

    @GetMapping("/findCategoryIdByList/{categoryId}")
    public Result findList(@PathVariable Long categoryId) {
        if (categoryId == null) {
            return Result.error();
        }

        List<PmsAttrGroup> list = attrGroupService.list();
        return Result.ok(list);
    }

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

    @GetMapping("/findAttrGroupById")
    public Result FindAttrGroupById(Long attrGroupId) {
        if (attrGroupId == null) {
            return Result.error();
        }

        PmsAttrGroup attrGroup = attrGroupService.getGroupById(attrGroupId);

        return Result.ok(attrGroup);
    }

    @PostMapping("/updateAttrGroupById")
    public Result updateAttrGroupById(@RequestBody PmsAttrGroup pmsAttrGroup) {
        attrGroupService.updateById(pmsAttrGroup);

        return Result.ok();
    }

    @PostMapping("/remove/{attrGroupId}")
    public Result remove(@PathVariable Long attrGroupId) {
        attrGroupService.removeById(attrGroupId);

        return Result.ok();
    }

    @GetMapping("/{attrGroupId}/attr/relation")
    public Result attrRelation(@PathVariable Long attrGroupId) {
        List<PmsAttr> attrs =  attrGroupService.categoryAttrList(attrGroupId);

        return Result.ok(attrs);
    }

    @PostMapping("/attr/relation/delete")
    public Result deleteAttrRelation(@RequestBody List<AttrGroupRelationVO> attrGroupRelationVOS) {
        attrGroupService.removeAttrRelation(attrGroupRelationVOS);

        return Result.ok();
    }

    @GetMapping("{attrGroupId}/noAttr/relation")
    public Result noAttrRelation(@PathVariable Long attrGroupId, Integer pageSize, Integer current, String keyword) {
        if (pageSize == null) {
            pageSize = 10;
        }

        if (current == null) {
            current = 0;
        }
        IPage<PmsAttr> attrIPage = attrGroupService.noAttrRelation(attrGroupId, pageSize, current, keyword);

        return Result.ok(attrIPage);
    }

    @PostMapping("/attr/relation")
    public Result addAttrRelation(@RequestBody List<AttrGroupRelationVO> attrGroupRelationVO) {
        if (attrGroupRelationVO == null || attrGroupRelationVO.size() <= 0) {
            return Result.error();
        }

        attrGroupService.addAttrRelation(attrGroupRelationVO);

        return Result.ok();
    }

    @GetMapping("/{catelogId}/withattr")
    public Result getAttrByAttrCategory(@PathVariable("catelogId") String categoryId) {
        List<AttrGroupWithAttr> attrByAttrCategory = attrGroupService.getAttrByAttrCategory(categoryId);

        return Result.ok(attrByAttrCategory);
    }
}
