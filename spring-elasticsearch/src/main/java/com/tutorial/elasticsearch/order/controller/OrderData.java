package com.tutorial.elasticsearch.order.controller;

import com.tutorial.elasticsearch.order.Order;

import java.time.format.DateTimeFormatter;

public class OrderData {
    private String id;

    private String createdDate;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public OrderData(Order order) {
        this.id = order.getId();
        this.createdDate = dateTimeFormatter.format(order.getCreatedDate());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
