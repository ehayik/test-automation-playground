package com.github.eljaiek.playgroud.guice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class MessageLoggerInterceptor implements MethodInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(MessageLoggerInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Stream.of(invocation.getArguments()).forEach(obj -> LOG.info("Sending message: {}", obj));
        return invocation.proceed();
    }
}