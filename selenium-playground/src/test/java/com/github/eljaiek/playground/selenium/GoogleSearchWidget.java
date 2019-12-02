package com.github.eljaiek.playground.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchWidget {

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    public GoogleSearchWidget(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void searchFor(String txt) {
        searchBox.sendKeys(txt);
        searchButton.click();
    }
}
