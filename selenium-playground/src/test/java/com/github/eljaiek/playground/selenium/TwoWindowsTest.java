package com.github.eljaiek.playground.selenium;

import com.github.eljaiek.autopilot.testng.AutopilotTest;
import lombok.val;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@AutopilotTest(modules = WebDriverModule.class)
public class TwoWindowsTest {

    @Inject
    private Environment environment;

    @Inject
    private WebDriver webDriver;

    @Test
    private void testTwoWindowsAtTheSameTime() {
        webDriver.get(environment.getGoogleUrl());
        val newWindowDriver = this.webDriver.switchTo().newWindow(WindowType.WINDOW);
        newWindowDriver.get(environment.getYoutubeUrl());
        assertThat(true).isTrue();
    }

    @AfterMethod
    public void tearDown() {
        webDriver.close();
    }
}
