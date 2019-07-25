package com.spring.data.tutorial.jpa.coffee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, String> {
}
