package com.spring.project.webflux.controller;

import com.spring.project.webflux.data.Plant;
import com.spring.project.webflux.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/plant")
    private ResponseEntity<?> savePlant(@RequestBody Plant plant) {
        plantRepository.save(plant);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/plants")
    private Flux<Plant> getPlants() {
        return plantRepository.findAll();
    }
}
