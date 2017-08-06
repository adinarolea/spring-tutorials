package com.tutorials.spring.caching.model;

import java.math.BigDecimal;

public class Person {

    private int age;
    private BigDecimal height;
    private Gender gender;

    public Person(int age, BigDecimal height, Gender gender) {
        this.age = age;
        this.height = height;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
