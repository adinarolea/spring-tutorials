package com.spring.data.tutorial.querydsl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "purses")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Purse {
    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal width;

    @Column(name = "plength")
    private BigDecimal length;

    @Column(name = "no_pockets")
    private int noPockets;

    @Column(name = "purse_type")
    @Enumerated(EnumType.STRING)
    private PurseType purseType;


    public enum PurseType {
        SPORT, CLASSY, CASUAL;
    }
}
