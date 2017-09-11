package com.tutorials.spring.caching;

import com.tutorials.spring.caching.model.Person;
import com.tutorials.spring.caching.weight.WeightCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static com.tutorials.spring.caching.model.Gender.FEMALE;
import static com.tutorials.spring.caching.model.Gender.MALE;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(CachingConfig.class)
@ComponentScan
public class WeightCalculatorTest {

    @Autowired
    WeightCalculator weightCalculator;

    @Autowired
    EhCacheCacheManager cacheManager;

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

    @Test
    public void testCorrectComputation() {
        assertEquals(BigDecimal.valueOf(61.8),
                weightCalculator.getIdealWeight(new Person(23, BigDecimal.valueOf(1.73), FEMALE)));
    }

    @Test
    @Timed(millis = 6500)
    public void testCacheHit() {
        Person person = new Person(23, BigDecimal.valueOf(1.73), FEMALE);
        weightCalculator.getIdealWeight(person);
        weightCalculator.getIdealWeight(person);
        weightCalculator.getIdealWeight(person);
    }

}
