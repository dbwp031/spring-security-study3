package com.example.springsecuritystudy3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(Model model) {
        return "main";
    }

    @GetMapping("/fail")
    public String fail() {
        return "fail";
    }

}
