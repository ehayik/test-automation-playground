package com.github.eljaiek.playgroud.guice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;

public class MessageLogger implements MethodInterceptor {

    private final Logger log;

    public MessageLogger(Logger log) {
        this.log = log;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object[] objectArray = invocation.getArguments();

        for (Object object : objectArray) {
            log.info("Sending message: {}", object);
        }

        return invocation.proceed();
    }
}