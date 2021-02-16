package com.spring.data.tutorial.querydsl.model;

import com.querydsl.core.types.dsl.BooleanExpression;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This builder class creates a {@link BooleanExpression} with configured predicates
 */
public class PursePredicateBuilder {

    private List<BooleanExpression> predicates = new ArrayList<>();
    private QPurse qPurse = QPurse.purse;

    /**
     * adds {@link Purse#width} as a predicate for the resulted query
     *
     * @return the current instance of the builder
     */
    public PursePredicateBuilder withWidth(BigDecimal width) {
        //optional parameter
        if (width == null) {
            return this;
        }
        predicates.add(qPurse.width.eq(width));
        return this;
    }
    /**
     * add {@link Purse#purseType} as a predicate
     * @return the current instance of the builder
     */
    public PursePredicateBuilder withLength(BigDecimal length) {
        if (length == null) {
            return this;
        }
        predicates.add(qPurse.length.eq(length));
        return this;
    }
    /**
     * add {@link Purse#purseType} as a predicate
     * @return the current instance of the builder
     */
    public PursePredicateBuilder withPurseType(Purse.PurseType purseType) {
        if (purseType == null) {
            return this;
        }
        QPurse qPurse = QPurse.purse;
        predicates.add(qPurse.purseType.eq(purseType));
        return this;
    }

    /**
     * @return a {@link BooleanExpression} with all predicates.
     */
    public BooleanExpression build(){
        BooleanExpression result = predicates.get(0);
        for (int i = 1; i < predicates.size(); i++) {
            result = result.and(predicates.get(i));
        }
        return result;
    }
}
