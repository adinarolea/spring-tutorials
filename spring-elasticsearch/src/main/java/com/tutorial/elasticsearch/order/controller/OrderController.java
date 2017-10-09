package com.tutorial.elasticsearch.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/")
    public String home() {
        return "index";
    }


}
