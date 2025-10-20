package com.sam.personalBlog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Controller

public class TemplateController {

    @GetMapping("/login")
    public String showLoginPage()
    {
        return "bruh";
    }
    @GetMapping("/welcome")
    public String showFirstPage(Model model, Authentication authentication)
    {
        Set<String> authorities =  new HashSet<>(authentication.getAuthorities().stream()
                                        .map(m -> m.getAuthority())
                                        .collect(Collectors.toList()));
        model.addAttribute("isAdmin",authorities.contains("ROLE_ADMIN"));
        model.addAttribute("name",authentication.getName());
        return "hello";
    }
}
