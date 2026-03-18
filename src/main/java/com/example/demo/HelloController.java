package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "자동 배포 성공! 🚀 Jenkins + Docker + Webhook";
    }

    @GetMapping("/version")
    public String version() {
        return "version 1.0";
    }
}
