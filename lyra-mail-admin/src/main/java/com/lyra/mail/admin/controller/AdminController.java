package com.lyra.mail.admin.controller;

import com.lyra.mail.admin.entity.Admin;
import com.lyra.mail.admin.service.IAdminService;
import com.lyra.mail.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", "123456");
        return Result.ok(tokenMap);
    }

    @GetMapping("/info")
    public Result Info(@RequestParam String token) {
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("roles", "admin");
        tokenMap.put("introduction", "My name is lyra heartstrings.");
        tokenMap.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        tokenMap.put("name", "Lyra Heartstrings.");
        return Result.ok(tokenMap);
    }

    @GetMapping("/list")
    public Result list() {
        List<Admin> list = adminService.list();

        return Result.ok(list);
    }
}
