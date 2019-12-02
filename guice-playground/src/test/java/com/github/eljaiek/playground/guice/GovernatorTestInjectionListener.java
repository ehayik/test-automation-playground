package com.github.eljaiek.playground.guice;

import com.google.inject.Module;
import lombok.SneakyThrows;
import lombok.val;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestNGException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GovernatorTestInjectionListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        var injector = result.getTestContext().getSuite().getParentInjector();
        val testCase = result.getInstance();
        val governatorTest = testCase.getClass().getAnnotation(GovernatorTest.class);

        if (governatorTest != null) {
            injector = injector.createChildInjector(loadModulesFor(governatorTest));
        }

        injector.injectMembers(result.getInstance());
    }

    @SneakyThrows
    private List<Module> loadModulesFor(GovernatorTest test) {
        return Stream.of(test.modules()).map(cl -> {
            try {
                return cl.getDeclaredConstructor().newInstance();
            } catch (ReflectiveOperationException ex) {
                throw new TestNGException(String.format("Module %s could not be created", cl.getName()), ex);
            }
        }).collect(Collectors.toList());
    }
}
