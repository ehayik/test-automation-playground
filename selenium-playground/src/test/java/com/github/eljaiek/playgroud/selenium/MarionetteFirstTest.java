package com.github.eljaiek.playgroud.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Slf4j
public class MarionetteFirstTest {

    private WebDriver driver;

    @BeforeClass
    public void initialize() {
      //  Enviroment.initialize();
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @AfterClass
    public void teardownTest () {
        driver.quit();
    }

    @Test
    public void checkTitleTest() {
        driver.navigate().to("http://www.swtestacademy.com/");
        driver.manage().window().maximize();
        var title = driver.getTitle();
        log.info("Page Title: {}", title);
        assertEquals(title, "Software Test Academy", "Title assertion is failed!");
    }
}
