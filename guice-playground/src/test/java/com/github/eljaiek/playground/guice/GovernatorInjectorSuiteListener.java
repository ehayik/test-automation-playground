package com.github.eljaiek.playground.guice;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.netflix.governator.guice.LifecycleInjector;
import com.netflix.governator.lifecycle.LifecycleManager;
import lombok.SneakyThrows;
import lombok.val;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.TestNGException;
import org.testng.internal.ClassHelper;

public class GovernatorInjectorSuiteListener implements ISuiteListener {

    private Injector injector;
    private LifecycleManager lifecycleManager;

    @SneakyThrows
    @Override
    public void onStart(ISuite suite) {

        if (!suite.getParentModule().isEmpty()) {
            val parentModuleClass = ClassHelper.forName(suite.getParentModule());

            if (parentModuleClass == null) {
                throw new TestNGException("Cannot load parent Guice module class: " + suite.getParentModule());
            }

            val parentModule = (Module) parentModuleClass.getDeclaredConstructor().newInstance();
            initInjectionContext(parentModule);

        } else {
            initInjectionContext(null);
        }

        suite.setParentInjector(injector);
    }

    @SneakyThrows
    private void initInjectionContext(Module parentModule) {

        if (parentModule != null) {
            injector = LifecycleInjector.builder()
                    .withModules(new CommModule())
                    .build()
                    .createInjector();
        } else {
            injector = LifecycleInjector.builder()
                    .build()
                    .createInjector();
        }

        lifecycleManager = injector.getInstance(LifecycleManager.class);
        lifecycleManager.start();
    }

    @Override
    public void onFinish(ISuite suite) {
        lifecycleManager.close();
    }
}
