package com.sam.per;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class MainPage {
    @GetMapping("/helloWorld")
    public String displayHtml(Model model)
    {
        List<String> list = List.of("Sam","Greg","Jay");
    model.addAttribute("name","Sam Hambunan");
    model.addAttribute("names",list);
    return "bruh";
    }
}
