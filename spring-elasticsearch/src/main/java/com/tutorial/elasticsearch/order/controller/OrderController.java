package com.tutorial.elasticsearch.order.controller;

import com.tutorial.elasticsearch.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/")
    public String home() {
        return "index";
    }


    @GetMapping("/orders")
    @ResponseBody
    public List<OrderData> getOrders() {
        return orderService.getAllOrders().stream().map(order -> new OrderData(order)).collect(Collectors.toList());
    }

}
