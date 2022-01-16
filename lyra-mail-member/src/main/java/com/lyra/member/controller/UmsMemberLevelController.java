package com.lyra.member.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.common.result.Result;
import com.lyra.member.entity.UmsMember;
import com.lyra.member.entity.UmsMemberLevel;
import com.lyra.member.service.IUmsMemberLevelService;
import com.lyra.member.service.IUmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 会员等级 前端控制器
 * </p>
 *
 * @author lyra
 * @since 2022-01-10
 */
@RestController
@RequestMapping("/ums-member-level")
public class UmsMemberLevelController {
    @Autowired
    private IUmsMemberLevelService memberLevelService;

    @GetMapping("/pageList")
    public Result list(Integer current, Integer pageSize) {

        if (current == null) {
            current = 0;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        Page<UmsMemberLevel> page = memberLevelService.page(new Page<>(current, pageSize));

        return Result.ok(page);
    }

    @GetMapping("/info/{memberLevelId}")
    public Result memberLevelInfo(@PathVariable Long memberLevelId) {
        UmsMemberLevel memberLevel = memberLevelService.getById(memberLevelId);
        return Result.ok(memberLevel);
    }

    @PostMapping("/save")
    public Result save(@RequestBody UmsMemberLevel umsMemberLevel) {
        memberLevelService.save(umsMemberLevel);

        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody UmsMemberLevel umsMemberLevel) {
        memberLevelService.updateById(umsMemberLevel);

        return Result.ok();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody List<Long> memberIds) {
        memberLevelService.removeByIds(memberIds);

        return Result.ok();
    }

    @GetMapping("/list")
    public Result list() {
        List<UmsMemberLevel> list = memberLevelService.list();

        return Result.ok(list);

    }
}
