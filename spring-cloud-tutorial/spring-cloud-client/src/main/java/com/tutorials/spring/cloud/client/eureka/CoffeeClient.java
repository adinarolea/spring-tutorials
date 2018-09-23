package com.tutorials.spring.cloud.client.eureka;

import org.springframework.web.bind.annotation.RequestMapping;

public interface CoffeeClient {
    @RequestMapping("/coffee")
    String getCoffee();
}