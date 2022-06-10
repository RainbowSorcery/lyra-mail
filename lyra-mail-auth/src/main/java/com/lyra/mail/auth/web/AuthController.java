package com.lyra.mail.auth.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        String originUrl = request.getParameter("originUrl");
        model.addAttribute("originUrl", originUrl);
        return "login";
    }
}
