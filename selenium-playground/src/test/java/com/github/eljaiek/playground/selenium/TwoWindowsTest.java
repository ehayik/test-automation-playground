package com.github.eljaiek.playground.selenium;

import com.github.eljaiek.autopilot.testng.AutopilotTest;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@AutopilotTest(modules = WebDriverModule.class)
public class TwoWindowsTest {

    @Inject
    private Environment env;

    @Inject
    private WebDriver webDriver;

    @Test
    private void testTwoWindowsAtTheSameTime() {
        log.info("Get page {}", env.getGoogleUrl());
        webDriver.get(env.getGoogleUrl());
        val newWindowDriver = this.webDriver.switchTo().newWindow(WindowType.WINDOW);
        log.info("Get page {}", env.getYoutubeUrl());
        newWindowDriver.get(env.getYoutubeUrl());
        assertThat(true).isTrue();
    }

    @AfterMethod
    public void tearDown() {
        webDriver.close();
    }
}
