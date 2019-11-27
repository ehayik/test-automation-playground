package com.github.eljaiek.playground.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class TestAspect {

    private static final Logger LOG = LoggerFactory.getLogger(TestAspect.class);

    @Pointcut("execution(* *(..)) && @annotation(org.testng.annotations.Test)")
    public void selectAllTests() {
    }

    @Before(value = "selectAllTests()")
    public void before(JoinPoint jp) {
        LOG.info("{}() method has been called", jp.getSignature().getName());
    }
}
