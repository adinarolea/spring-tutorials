package com.spring.data.tutorial.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CoffeeController {

    @Autowired
    CoffeeRepository coffeeRepository;

    @RequestMapping(value = "/coffee", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody ResponseEntity<Coffee> getCoffeeById(HttpServletRequest request, @RequestParam String id) {
        return new ResponseEntity<>(coffeeRepository.findById(id), HttpStatus.OK);
    }
}
