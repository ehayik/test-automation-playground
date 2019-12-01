package com.github.eljaiek.autopilot.testng;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.netflix.governator.configuration.PropertiesConfigurationProvider;
import com.netflix.governator.guice.BootstrapModule;
import com.netflix.governator.guice.LifecycleInjector;
import com.netflix.governator.lifecycle.LifecycleManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.TestNGException;
import org.testng.internal.ClassHelper;

import java.util.Properties;

@Slf4j
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
            createInjector(parentModule);

        } else {
            createInjector(null);
        }

        suite.setParentInjector(injector);
    }

    @SneakyThrows
    private void createInjector(Module parentModule) {
        var injectorBuilder = LifecycleInjector.builder();
        val bootstrapModule = getBootstrapModule();

        if (bootstrapModule != null) {
            injectorBuilder.withBootstrapModule(bootstrapModule);
        }

        if (parentModule != null) {
            injectorBuilder.withModules(parentModule);
        }

        injector = injectorBuilder.build().createInjector();

        lifecycleManager = injector.getInstance(LifecycleManager.class);
        lifecycleManager.start();
    }

    @SneakyThrows
    private BootstrapModule getBootstrapModule() {
        BootstrapModule bm = null;
        val inputStream = AutopilotSuiteListener.class.getResourceAsStream("/environment.properties");

        if (inputStream != null) {
            val props = new Properties();
            props.load(inputStream);
            val configProvider = new PropertiesConfigurationProvider(props);
            bm = binder -> binder.bindConfigurationProvider().toInstance(configProvider);
        } else {
            log.warn("The environment.properties file is not found. Injector is going to be created without BootstrapModule.");
        }

        return bm;
    }

    @Override
    public void onFinish(ISuite suite) {
        lifecycleManager.close();
    }
}
