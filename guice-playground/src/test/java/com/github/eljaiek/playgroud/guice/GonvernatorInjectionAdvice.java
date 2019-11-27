package com.github.eljaiek.playgroud.guice;

import com.google.inject.Injector;
import com.netflix.governator.guice.LifecycleInjector;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class GonvernatorInjectionAdvice {

    @Pointcut("execution(* *(..)) && @annotation(org.testng.annotations.Test)")
    public void selectAllTests() {
    }

    @SneakyThrows
    @Before(value = "selectAllTests()")
    public void before(JoinPoint jp) {
        Injector injector = LifecycleInjector.builder()
                .withModules(new CommModule())
                .build()
                .createInjector();
        injector.injectMembers(jp.getThis());
    }
}
