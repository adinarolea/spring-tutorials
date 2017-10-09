package com.tutorial.elasticsearch.order.service;

import com.tutorial.elasticsearch.order.Order;
import com.tutorial.elasticsearch.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        Page<Order> orderPage = orderRepository.findAll();
        if (orderPage == null) {
            return Collections.emptyList();
        }
        return orderPage.getContent();
    }
}
