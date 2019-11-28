package com.github.eljaiek.autopilot.testng;

import com.google.inject.Module;
import lombok.SneakyThrows;
import lombok.val;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestNGException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AutopilotTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        var injector = result.getTestContext().getSuite().getParentInjector();
        val testCase = result.getInstance();
        val autopilotTest = testCase.getClass().getAnnotation(AutopilotTest.class);

        if (autopilotTest != null) {
            injector = injector.createChildInjector(loadModulesFor(autopilotTest));
        }

        injector.injectMembers(result.getInstance());
    }

    @SneakyThrows
    private List<Module> loadModulesFor(AutopilotTest test) {
        return Stream.of(test.modules()).map(cl -> {
            try {
                return cl.getDeclaredConstructor().newInstance();
            } catch (ReflectiveOperationException ex) {
                throw new TestNGException(String.format("Module %s could not be created", cl.getName()), ex);
            }
        }).collect(Collectors.toList());
    }
}
