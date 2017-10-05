package com.tutorials.spring.caching.weight;

import com.tutorials.spring.caching.model.Person;

import java.math.BigDecimal;

public interface WeightCalculator {
    /**
     * calculates the ideal weight of a person, based on age,height and gender
     * if the input person object is null, or the above fields are not declared, {@link IllegalArgumentException} exception will be thrown
     *
     * @param person
     * @return the ideal weight of the input {@link Person}
     */
    BigDecimal getIdealWeight(Person person);
}
