package com.github.eljaiek.playgroud.guice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.stream.Stream;

@Slf4j
public class MessageLoggerInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Stream.of(invocation.getArguments()).forEach(obj -> log.info("Sending message: {}", obj));
        return invocation.proceed();
    }
}