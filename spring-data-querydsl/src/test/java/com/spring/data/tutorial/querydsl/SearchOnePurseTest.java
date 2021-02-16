package com.spring.data.tutorial.querydsl;

import com.spring.data.tutorial.querydsl.model.Purse;
import com.spring.data.tutorial.querydsl.model.PursePredicateBuilder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * This test has the purpose to verify if the search with a predicate returns the correct result
 * The input data should be only one purse that matches the input predicate
 */
public class SearchOnePurseTest extends AbstractPurseTest {

    /**
     * predicate: width = 5.7
     */
    @Test
    public void searchOneByWidth() {
        PursePredicateBuilder pursePredicateBuilder = new PursePredicateBuilder();
        pursePredicateBuilder.withWidth(BigDecimal.valueOf(5.7));
        Optional<Purse> purseOpt = purseRepository.findOne(pursePredicateBuilder.build());
        assertTrue(purseOpt.isPresent());
        Purse purse = purseOpt.get();
        assertEquals(0, BigDecimal.valueOf(5.7).compareTo(purse.getWidth()));
    }

    /**
     * predicate: length = 10.5
     */
    @Test
    public void searchOneByLength() {
        PursePredicateBuilder pursePredicateBuilder = new PursePredicateBuilder();
        pursePredicateBuilder.withLength(BigDecimal.valueOf(10.5));
        Optional<Purse> purseOpt = purseRepository.findOne(pursePredicateBuilder.build());
        assertTrue(purseOpt.isPresent());
        Purse purse = purseOpt.get();
        assertEquals(0, BigDecimal.valueOf(10.5).compareTo(purse.getLength()));
    }

    /**
     * predicate: purseType = CLASSY
     */
    @Test
    public void searchOneByPurseType() {
        PursePredicateBuilder pursePredicateBuilder = new PursePredicateBuilder();
        pursePredicateBuilder.withPurseType(Purse.PurseType.CLASSY);
        Optional<Purse> purseOpt = purseRepository.findOne(pursePredicateBuilder.build());
        assertTrue(purseOpt.isPresent());
        Purse purse = purseOpt.get();
        assertEquals(Purse.PurseType.CLASSY, purse.getPurseType());
    }

    /**
     * predicate: length = 13.5 and width = 12.1
     */
    @Test
    public void searchOneByLengthAndWidth() {
        PursePredicateBuilder pursePredicateBuilder = new PursePredicateBuilder();
        pursePredicateBuilder
                .withLength(BigDecimal.valueOf(13.5))
                .withWidth(BigDecimal.valueOf(12.1));
        Optional<Purse> purseOpt = purseRepository.findOne(pursePredicateBuilder.build());
        assertTrue(purseOpt.isPresent());
        Purse purse = purseOpt.get();
        assertEquals(0, BigDecimal.valueOf(13.5).compareTo(purse.getLength()));
        assertEquals(0, BigDecimal.valueOf(12.1).compareTo(purse.getWidth()));

    }
}
