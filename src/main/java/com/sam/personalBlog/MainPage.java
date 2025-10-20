package com.sam.personalBlog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/test")
public class MainPage {
    int x = 0;
    @GetMapping("/button")
    public String buttonHtml()
    {
    return "button";
    }
    @PostMapping("/buttonClicked")
    public String clickedButton(Model model)
    {
        model.addAttribute("clicked",true);
        return "button";
    }

}
