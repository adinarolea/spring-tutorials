package com.tutorials.spring.caching.weight;

import com.tutorials.spring.caching.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Service
public class DefaultWeightCalculator implements WeightCalculator {

    @Override
    public BigDecimal getIdealWeight(Person person) {
        Assert.notNull(person, "Person is undefined");
        Assert.notNull(person.getGender(), "Age is undefined");
        Assert.notNull(person.getHeight(), "Height is undefined");
        if (person.getAge() <= 0) {
            throw new IllegalArgumentException("Age cannot be negative or equal to 0");
        }
        return null;
    }
}
