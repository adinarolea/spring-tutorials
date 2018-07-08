package com.tutorials.spring.eureka.client;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeController implements CoffeeClient{

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public String getCoffee() {
        return String.format("Hello from '%s'!", eurekaClient.getApplication(applicationName).getName());
    }
}
