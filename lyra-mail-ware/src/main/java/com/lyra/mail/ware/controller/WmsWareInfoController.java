package com.lyra.mail.ware.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.ware.entity.WmsWareInfo;
import com.lyra.mail.ware.service.IWmsWareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 仓库信息 前端控制器
 * </p>
 *
 * @author lyra
 * @since 2022-01-16
 */
@RestController
@RequestMapping("/wms-ware-info")
public class WmsWareInfoController {
    @Autowired
    private IWmsWareInfoService wareInfoService;

    @GetMapping("/list")
    public Result list(Integer current, Integer pageSize, String key) {
        if (current == null) {
            current = 0;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        Page<WmsWareInfo> wmsWareInfoPage = wareInfoService.pageList(current, pageSize, key);

        return Result.ok(wmsWareInfoPage);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody List<Long> ids) {
        wareInfoService.removeByIds(ids);

        return Result.ok();
    }

    @PostMapping("/save")
    public Result save(@RequestBody WmsWareInfo wmsWareInfo) {
        wareInfoService.save(wmsWareInfo);

        return Result.ok();
    }

    @GetMapping("/info/{id}")
    public Result info(@PathVariable Long id) {
        WmsWareInfo wmsWareInfo = wareInfoService.getById(id);

        return Result.ok(wmsWareInfo);
    }

    @PostMapping("/update")
    public Result update(@RequestBody WmsWareInfo wmsWareInfo) {
        wareInfoService.updateById(wmsWareInfo);

        return Result.ok();
    }
}
