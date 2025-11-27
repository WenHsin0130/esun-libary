package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloGradle {

    @GetMapping("/")
    // 當使用者在瀏覽器輸入 http://localhost:8080/ 時出現以下訊息
    public String home() {
        return "backend running...";
    }
}