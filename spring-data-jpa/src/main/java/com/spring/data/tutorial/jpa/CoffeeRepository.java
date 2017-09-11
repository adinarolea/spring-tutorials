package com.spring.data.tutorial.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, String> {

    Coffee findById(String id);
}
