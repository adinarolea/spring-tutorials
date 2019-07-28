package com.spring.project.webflux.repository;

import com.spring.project.webflux.data.Plant;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PlantRepository extends ReactiveMongoRepository<Plant,String> {
}
