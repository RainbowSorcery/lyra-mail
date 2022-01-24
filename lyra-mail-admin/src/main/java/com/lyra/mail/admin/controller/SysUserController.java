package com.lyra.mail.admin.controller;


import com.lyra.mail.admin.entity.SysUser;
import com.lyra.mail.admin.service.ISysUserService;
import com.lyra.mail.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author lyra
 * @since 2022-01-23
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {
    @Autowired
    private ISysUserService userService;

    @GetMapping("/list")
    public Result list() {
        List<SysUser> list = userService.list();

        return Result.ok(list);
    }

}
