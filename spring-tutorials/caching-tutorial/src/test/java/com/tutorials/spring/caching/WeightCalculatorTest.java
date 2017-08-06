package com.tutorials.spring.caching;

import com.tutorials.spring.caching.model.Gender;
import com.tutorials.spring.caching.model.Person;
import com.tutorials.spring.caching.weight.WeightCalculator;
import com.tutorials.spring.caching.weight.WeightCalculatorException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static com.tutorials.spring.caching.model.Gender.FEMALE;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeightCalculatorTest {

    @Autowired
    WeightCalculator weightCalculator;

    @Test(expected = WeightCalculatorException.class)
    public void testWithNullPerson() {
        weightCalculator.getIdealWeight(null);
    }

    @Test(expected = WeightCalculatorException.class)
    public void testWithoutHeight() {
        weightCalculator.getIdealWeight(new Person(23, null, FEMALE));
    }

    @Test(expected = WeightCalculatorException.class)
    public void testWithoutNegativeAge() {
        weightCalculator.getIdealWeight(new Person(-2, BigDecimal.valueOf(1.73), FEMALE));
    }

    @Test(expected = WeightCalculatorException.class)
    public void testWithoutZeroAge() {
        weightCalculator.getIdealWeight(new Person(0, BigDecimal.valueOf(1.73), FEMALE));
    }

    @Test(expected = WeightCalculatorException.class)
    public void testWithoutGender() {
        weightCalculator.getIdealWeight(new Person(23, BigDecimal.valueOf(1.73), null));
    }
}
