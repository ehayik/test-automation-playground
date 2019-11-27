package com.github.eljaiek.playgroud.selenium;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.testng.Assert.assertEquals;

@Slf4j
@Guice(modules = MarionetteModule.class)
public class MarionetteFirstTest {

    @Inject
    private WebDriver driver;

    @AfterClass
    public void teardownTest () {
        driver.quit();
    }

    @Test
    public void checkTitleTest() {
        driver.navigate().to("http://www.swtestacademy.com/");
        driver.manage().window().maximize();
        val title = driver.getTitle();
        log.info("Page Title: {}", title);
        assertEquals(title, "Software Test Academy", "Title assertion is failed!");
    }
}
