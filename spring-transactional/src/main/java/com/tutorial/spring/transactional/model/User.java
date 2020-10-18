package com.tutorial.spring.transactional.model;

import lombok.Builder;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Value
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;
}
