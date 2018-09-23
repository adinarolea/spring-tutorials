package com.tutorials.spring.cloud.client.consumer.eureka;

import com.tutorials.spring.cloud.client.eureka.CoffeeClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("spring-cloud-client")
public interface FeignCoffee extends CoffeeClient {
}