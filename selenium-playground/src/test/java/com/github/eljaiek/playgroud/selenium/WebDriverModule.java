package com.github.eljaiek.playgroud.selenium;

import com.google.inject.AbstractModule;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.val;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class WebDriverModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(WebDriver.class).toProvider(() -> {
            WebDriverManager.firefoxdriver().setup();
            val driver = new FirefoxDriver();
            WebDriverHolder.setWebDriver(driver);
            return driver;
        });
    }
}
