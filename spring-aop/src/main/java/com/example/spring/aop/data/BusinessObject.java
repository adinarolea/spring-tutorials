package com.example.spring.aop.data;

public class BusinessObject {

    private Integer inputNumber;

    private Integer outputNumber;

    public Integer getInputNumber() {
        return inputNumber;
    }

    public BusinessObject setInputNumber(Integer inputNumber) {
        this.inputNumber = inputNumber;
        return this;
    }

    public Integer getOutputNumber() {
        return outputNumber;
    }

    public BusinessObject setOutputNumber(Integer outputNumber) {
        this.outputNumber = outputNumber;
        return this;
    }

    public void throwException(){
        throw new RuntimeException("Business object exception");
    }
}
