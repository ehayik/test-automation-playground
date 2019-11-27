package com.github.eljaiek.playgroud.guice;

import com.google.inject.Injector;
import com.netflix.governator.guice.LifecycleInjector;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class GovernatorListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Injector injector = LifecycleInjector.builder()
                .withModules(new CommModule())
                .build()
                .createInjector();
        injector.injectMembers(result.getInstance());
    }
}
