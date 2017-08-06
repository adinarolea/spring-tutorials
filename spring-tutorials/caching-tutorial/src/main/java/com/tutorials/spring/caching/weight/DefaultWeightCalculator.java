package com.tutorials.spring.caching.weight;

import com.tutorials.spring.caching.model.Gender;
import com.tutorials.spring.caching.model.Person;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DefaultWeightCalculator implements WeightCalculator {

    @Override
    @Cacheable("api-cache")
    public BigDecimal getIdealWeight(Person person) {
        Assert.notNull(person, "Person is undefined");
        Assert.notNull(person.getGender(), "Age is undefined");
        Assert.notNull(person.getHeight(), "Height is undefined");
        if (person.getAge() <= 0) {
            throw new IllegalArgumentException("Age cannot be negative or equal to 0");
        }
        // simulate api call, sleep for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (person.getGender().equals(Gender.MALE)) {
            return (person.getHeight().multiply(BigDecimal.valueOf(100))
                    .subtract(BigDecimal.valueOf(80)).multiply(BigDecimal.valueOf(0.7))).setScale(1, RoundingMode.HALF_UP);
        }
        return (person.getHeight().multiply(BigDecimal.valueOf(100))
                .subtract(BigDecimal.valueOf(70)).multiply(BigDecimal.valueOf(0.6))).setScale(1, RoundingMode.HALF_UP);
    }
}
