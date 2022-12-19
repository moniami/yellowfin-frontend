package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AboutController {
    @GetMapping("/about")
    public String about(@RequestParam(name = "name", defaultValue = "About Us") String param, Model model) {
        model.addAttribute("name",param);
        return "about";
    }

}