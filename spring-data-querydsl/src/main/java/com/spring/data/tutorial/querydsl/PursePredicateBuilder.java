package com.spring.data.tutorial.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This builder class creates a {@link BooleanExpression} with configured predicates
 */
public class PursePredicateBuilder {

    List<BooleanExpression> predicates = new ArrayList<>();

    /**
     * adds {@link Purse#width} as a predicate for the resulted query
     * @return the current instance of the builder
     */
    public PursePredicateBuilder withWidth(BigDecimal width) {
        PathBuilder<Purse> entityPath = new PathBuilder<>(Purse.class, "purse");
        NumberPath<BigDecimal> numberPath = entityPath.getNumber("width", BigDecimal.class);
        predicates.add(numberPath.eq(width));
        return this;
    }
    /**
     * add {@link Purse#purseType} as a predicate
     * @return the current instance of the builder
     */
    public PursePredicateBuilder withLength(BigDecimal length) {
        QPurse qPurse = QPurse.purse;
        predicates.add(qPurse.length.eq(length));
        return this;
    }
    /**
     * add {@link Purse#purseType} as a predicate
     * @return the current instance of the builder
     */
    public PursePredicateBuilder withPurseType(Purse.PurseType purseType) {
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
