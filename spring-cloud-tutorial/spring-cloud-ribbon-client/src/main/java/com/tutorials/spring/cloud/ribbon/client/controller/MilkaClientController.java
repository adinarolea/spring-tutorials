package com.tutorials.spring.cloud.ribbon.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MilkaClientController {

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/mu")
    public String helloMilka() {
       // ServiceInstance instance = loadBalancer.choose("milka");
        //URI storesUri = URI.create(String.format("http://milka/greeting", instance.getHost(), instance.getPort()));
        return this.restTemplate.getForObject("http://milka/greeting", String.class);
    }

}
