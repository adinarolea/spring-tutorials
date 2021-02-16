package com.spring.data.tutorial.querydsl;

import com.spring.data.tutorial.querydsl.model.Purse;
import com.spring.data.tutorial.querydsl.repository.PurseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static java.util.Arrays.asList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractPurseTest {

    @Autowired
    PurseRepository purseRepository;

    @BeforeEach
    public void init() {
        if (purseRepository.count() > 0) {
            return;
        }
        Purse purse1 = Purse.builder()
                .width(new BigDecimal("11.1"))
                .length(new BigDecimal("10.5"))
                .noPockets(2)
                .purseType(Purse.PurseType.CASUAL)
                .build();

        Purse purse2 = Purse.builder()
                .width(new BigDecimal("11.1"))
                .length(new BigDecimal("5.5"))
                .noPockets(1)
                .purseType(Purse.PurseType.CASUAL)
                .build();

        Purse purse3 = Purse.builder()
                .width(new BigDecimal("5.7"))
                .length(new BigDecimal("5.5"))
                .noPockets(1)
                .purseType(Purse.PurseType.CLASSY)
                .build();
        Purse purse4 = Purse.builder()
                .width(new BigDecimal("12.1"))
                .length(new BigDecimal("13.5"))
                .noPockets(4)
                .purseType(Purse.PurseType.SPORT)
                .build();
        Purse purse5 = Purse.builder()
                .width(new BigDecimal("11.1"))
                .length(new BigDecimal("5.5"))
                .noPockets(2)
                .purseType(Purse.PurseType.SPORT)
                .build();
        purseRepository.saveAll(asList(purse1, purse2, purse3, purse4, purse5));
    }
}
