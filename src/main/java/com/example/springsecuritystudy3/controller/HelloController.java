package com.example.springsecuritystudy3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String main() {
        return "main";
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
