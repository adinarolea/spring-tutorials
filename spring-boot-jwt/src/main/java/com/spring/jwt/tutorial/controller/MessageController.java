package com.spring.jwt.tutorial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping("/message/secret")
    public String getSecretMessage() {
        return "Hello, only you can see this message!";
    }

    @GetMapping("/message/public")
    public String getPublicMessage() {
        return "Hello, this a public message";
    }
}
