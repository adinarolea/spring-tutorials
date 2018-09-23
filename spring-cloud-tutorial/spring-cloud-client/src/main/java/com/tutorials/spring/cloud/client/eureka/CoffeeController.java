package com.tutorials.spring.cloud.client.eureka;

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

    @Value("${test.config:test}")
    String config;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public String getCoffee() {
        return String.format("Hello from '%s' %s!", eurekaClient.getApplication(applicationName).getName(),config);
    }
}
