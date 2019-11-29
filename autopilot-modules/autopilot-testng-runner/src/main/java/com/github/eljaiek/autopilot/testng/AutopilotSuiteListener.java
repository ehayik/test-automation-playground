package com.github.eljaiek.autopilot.testng;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.netflix.governator.configuration.PropertiesConfigurationProvider;
import com.netflix.governator.guice.BootstrapModule;
import com.netflix.governator.guice.LifecycleInjector;
import com.netflix.governator.lifecycle.LifecycleManager;
import lombok.SneakyThrows;
import lombok.val;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.TestNGException;
import org.testng.internal.ClassHelper;

import java.util.Properties;

public class AutopilotSuiteListener implements ISuiteListener {

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
                    .withBootstrapModule(getBootstrapModule())
                    .withModules(parentModule)
                    .build()
                    .createInjector();
        } else {
            injector = LifecycleInjector.builder()
                    .withBootstrapModule(getBootstrapModule())
                    .build()
                    .createInjector();
        }

        lifecycleManager = injector.getInstance(LifecycleManager.class);
        lifecycleManager.start();
    }

    @SneakyThrows
    private BootstrapModule getBootstrapModule() {
        val props = new Properties();
        props.load(AutopilotSuiteListener.class.getResourceAsStream("/environment.properties"));
        val configProvider = new PropertiesConfigurationProvider(props);
        return binder -> binder.bindConfigurationProvider().toInstance(configProvider);
    }

    @Override
    public void onFinish(ISuite suite) {
        lifecycleManager.close();
    }
}
