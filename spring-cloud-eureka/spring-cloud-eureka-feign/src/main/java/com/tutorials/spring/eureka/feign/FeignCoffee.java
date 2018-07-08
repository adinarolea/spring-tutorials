package com.tutorials.spring.eureka.feign;

import com.tutorials.spring.eureka.client.CoffeeClient;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("spring-cloud-eureka-client")
public interface FeignCoffee extends CoffeeClient {
}