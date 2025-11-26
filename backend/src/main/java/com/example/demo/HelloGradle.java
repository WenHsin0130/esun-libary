package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloGradle {

    @GetMapping("/")
    public String home() {
        return "測試頁面!";
    }
}