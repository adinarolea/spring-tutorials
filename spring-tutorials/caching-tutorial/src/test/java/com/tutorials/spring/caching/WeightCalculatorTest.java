package com.tutorials.spring.caching;

import com.tutorials.spring.caching.model.Person;
import com.tutorials.spring.caching.weight.WeightCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static com.tutorials.spring.caching.model.Gender.FEMALE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(CachingConfig.class)
@ComponentScan
public class WeightCalculatorTest {

    @Autowired
    WeightCalculator weightCalculator;

    @Test(expected = IllegalArgumentException.class)
    public void testWithNullPerson() {
        weightCalculator.getIdealWeight(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithoutHeight() {
        weightCalculator.getIdealWeight(new Person(23, null, FEMALE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithoutNegativeAge() {
        weightCalculator.getIdealWeight(new Person(-2, BigDecimal.valueOf(1.73), FEMALE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithoutZeroAge() {
        weightCalculator.getIdealWeight(new Person(0, BigDecimal.valueOf(1.73), FEMALE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithoutGender() {
        weightCalculator.getIdealWeight(new Person(23, BigDecimal.valueOf(1.73), null));
    }
}
