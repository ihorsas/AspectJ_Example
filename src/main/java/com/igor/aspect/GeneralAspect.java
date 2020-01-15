package com.igor.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

@Aspect
public class GeneralAspect {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    public GeneralAspect() {
    }

    @Pointcut("execution(* *.talk*(..))")
    private void talkMethod() {
    }

    @Pointcut("execution(* *.isCreated*(..))")
    private void isCreated() {
    }

    @Before("talkMethod()")
    public void talkBefore(JoinPoint joinPoint) {
        logger.warn("BEFORE: " + joinPoint.getTarget().getClass().getSimpleName() + " " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "isCreated()", returning = "returnedValue")
    public void isCreatedAfter(JoinPoint joinPoint, Object returnedValue) {
        logger.warn("After: " + joinPoint.getTarget().getClass().getSimpleName() + " " + joinPoint.getSignature().getName());
        logger.warn("Returned value: " + returnedValue);
    }

    @AfterThrowing(pointcut = "talkMethod()", throwing = "ex")
    public void talkAfterThrow(JoinPoint joinPoint, Throwable ex) {
        logger.warn("After: " + joinPoint.getTarget().getClass().getSimpleName() + " " + joinPoint.getSignature().getName());
        logger.warn("Thrown: " + ex);
    }
}
