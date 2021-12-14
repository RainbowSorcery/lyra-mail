package com.lyra.mail.admin.controller;

import com.lyra.mail.admin.model.Admin;
import com.lyra.mail.common.result.Result;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
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
}
