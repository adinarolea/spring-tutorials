package com.spring.project.webflux.controller;

import com.spring.project.webflux.data.Plant;
import com.spring.project.webflux.data.PlantEvent;
import com.spring.project.webflux.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@RestController
public class PlantController {

    @Autowired
    PlantRepository plantRepository;

    @GetMapping("/plant/{id}")
    private Mono<Plant> getPlant(@PathVariable String id) throws InterruptedException {
       return plantRepository.findById(id);
    }

    @GetMapping(value = "/plant/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<PlantEvent> getPlantEvents(@PathVariable String id) {
        return plantRepository.findById(id)
                .flatMapMany(plant -> {
                    Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
                    Flux<PlantEvent> plantEventFlux = Flux.fromStream(Stream.generate(() -> new PlantEvent(plant, new Date())));
                    return Flux.zip(interval, plantEventFlux)
                            .map(Tuple2::getT2);
                });
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
