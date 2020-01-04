package com.example.spring.aop.controller;


import com.example.spring.aop.aspect.MyCustomAnnotation;
import com.example.spring.aop.data.BusinessObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AspectController {


    @GetMapping("/test/{value}")
    @MyCustomAnnotation
    public BusinessObject getObject(@PathVariable Integer value){
        return new BusinessObject()
                .setInputNumber(value);
    }
}
