package com.github.eljaiek.playground.selenium;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class MarionetteModule extends AbstractModule {

    @Override
    protected void configure() { }

    @Provides
    public WebDriver provideWebDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
