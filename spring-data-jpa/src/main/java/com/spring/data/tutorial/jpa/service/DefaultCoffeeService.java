package com.spring.data.tutorial.jpa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.data.tutorial.jpa.coffee.Coffee;
import com.spring.data.tutorial.jpa.coffee.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class DefaultCoffeeService implements CoffeeService {

    @Autowired
    CoffeeRepository coffeeRepository;

    @PostConstruct
    public void initCoffee() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Coffee> coffeeList = objectMapper.readValue(this.getClass().getClassLoader().getResourceAsStream("coffee.json"),
                new TypeReference<List<Coffee>>() {
                });
        coffeeRepository.saveAll(coffeeList);
    }

    @Override
    public Optional<Coffee> getCoffee(String id) {
        return coffeeRepository.findById(id);
    }
}
