package com.spring.data.tutorial.jpa.service;

import com.spring.data.tutorial.jpa.coffee.Coffee;

import java.util.Optional;

public interface CoffeeService {

    Optional<Coffee> getCoffee(String id);
}
