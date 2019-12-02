package com.github.eljaiek.playground.selenium;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.openqa.selenium.WebDriver;

public class GoogleWidgetsModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    public GoogleSearchWidget provideGoogleSearchWidget(WebDriver webDriver) {
        return new GoogleSearchWidget(webDriver);
    }

    @Provides
    public GoogleSearchResult provideGoogleSearchResult(WebDriver webDriver) {
        return new GoogleSearchResult(webDriver);
    }
}
