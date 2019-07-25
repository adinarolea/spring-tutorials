package com.spring.data.tutorial.jpa.controller;

import com.spring.data.tutorial.jpa.NotFoundException;
import com.spring.data.tutorial.jpa.coffee.Coffee;
import com.spring.data.tutorial.jpa.coffee.CoffeeRepository;
import com.spring.data.tutorial.jpa.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CoffeeController {

    @Autowired
    CoffeeService coffeeService;

    @GetMapping(value = "/coffee/{id}")

    public @ResponseBody ResponseEntity<Coffee> getCoffeeById(@PathVariable String id) {
        return coffeeService.getCoffee(id)
                .map(coffee -> ResponseEntity.ok(coffee))
                .orElseThrow(NotFoundException::new);
    }
}
