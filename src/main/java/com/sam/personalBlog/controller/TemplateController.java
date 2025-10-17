package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("/login")
    public String showLoginPage()
    {
        return "bruh";
    }
    @GetMapping("/welcome")
    public String showFirstPage(Model model, Authentication authentication)
    {
        Set<GrantedAuthority> authorities =  new HashSet<>(authentication.getAuthorities());
        model.addAttribute("isAdmin",authorities.contains("ROLE_ADMIN"));
        model.addAttribute("name",authentication.getName());
        return "hello";
    }
}
