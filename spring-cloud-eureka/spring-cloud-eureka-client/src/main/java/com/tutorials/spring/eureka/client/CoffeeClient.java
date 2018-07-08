package com.tutorials.spring.eureka.client;

import org.springframework.web.bind.annotation.RequestMapping;

public interface CoffeeClient {
    @RequestMapping("/coffee")
    String getCoffee();
}