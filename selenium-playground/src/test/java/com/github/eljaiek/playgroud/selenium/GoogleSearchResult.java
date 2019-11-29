package com.github.eljaiek.playgroud.selenium;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class GoogleSearchResult {

    @FindBy(className = "rc")
    private List<WebElement> results;

    public GoogleSearchResult(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public int getCount() {
        return results.size();
    }

    public void displayResult() {
        results.stream()
            .map(WebElement::getText)
            .forEach(log::info);
    }
}