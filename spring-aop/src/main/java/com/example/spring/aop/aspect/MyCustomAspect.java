package com.example.spring.aop.aspect;

import com.example.spring.aop.data.BusinessObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyCustomAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyCustomAspect.class);

    @Around("@annotation(MyCustomAnnotation)")
    public Object annotationAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        Object object = joinPoint.proceed();
        if (!(object instanceof BusinessObject)) {
            return object;
        }
        BusinessObject businessObject = (BusinessObject) object;
        businessObject.setOutputNumber(businessObject.getInputNumber() + 1);
        return businessObject;
    }

    @AfterThrowing("@annotation(MyCustomAnnotation)")
    public void doRecoveryActions() {
        LOGGER.info("This is an advice");
    }

}