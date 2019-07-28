package com.spring.project.webflux.controller;

import com.spring.project.webflux.data.Plant;
import com.spring.project.webflux.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PlantController {

    @Autowired
    PlantRepository plantRepository;

    @GetMapping("/plant/{id}")
    private Mono<Plant> getPlant(@PathVariable String id) {
        return plantRepository.findById(id);
    }

    @GetMapping("/plants")
    private Flux<Plant> getPlants() {
        return plantRepository.findAll();
    }
}
