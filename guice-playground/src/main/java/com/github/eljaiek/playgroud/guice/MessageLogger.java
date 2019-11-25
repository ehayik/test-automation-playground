package com.github.eljaiek.playgroud.guice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class MessageLogger implements MethodInterceptor {
 
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object[] objectArray = invocation.getArguments();

        for (Object object : objectArray) {
            log.info("Sending message: {}", object);
        }

        return invocation.proceed();
    }
}