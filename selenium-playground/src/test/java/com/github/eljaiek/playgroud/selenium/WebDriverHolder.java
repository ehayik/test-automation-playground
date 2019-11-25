package com.github.eljaiek.playgroud.selenium;

import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;
import java.util.Optional;

public final class WebDriverHolder {

    private static ThreadLocal<Optional<WebDriver>> CURRENT_WEBDRIVER = ThreadLocal.withInitial(Optional::empty);

    public static WebDriver getWebDriver() {
        return CURRENT_WEBDRIVER.get().orElseThrow(() -> new NoSuchElementException("WebDriver is not configured"));
    }

    static void setWebDriver(WebDriver webDriver) {
        CURRENT_WEBDRIVER.set(Optional.of(webDriver));
    }
}
