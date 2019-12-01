package com.github.eljaiek.playgroud.selenium;

import com.google.inject.Inject;
import com.netflix.governator.annotations.Configuration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class GooglePage {

    private final WebDriver driver;

    @Inject
    @Configuration("app.url")
    private String url;

    @Inject
    private GoogleSearchWidget searchBox;

    @Inject
    private GoogleSearchResult searchResult;

    @Inject
    private JavascriptExecutor jsExecutor;

    @Inject
    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo() {
        driver.get(url);
    }

    public void searchFor(String txt) {
        searchBox.searchFor(txt);
    }

    public void displayResult() {
        searchResult.displayResult();
    }

    public int getSearchResultCount() {
        return searchResult.getCount();
    }

    public Object execute(String script) {
        return jsExecutor.executeScript(script);
    }
}
