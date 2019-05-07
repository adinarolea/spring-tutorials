package com.tutorials.spring.cloud.ribbon.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MilkController {

    @GetMapping("/greeting")
    public String milka() {
        return "Hello there";
    }
}
