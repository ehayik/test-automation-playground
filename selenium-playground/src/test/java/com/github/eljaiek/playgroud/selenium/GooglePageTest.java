package com.github.eljaiek.playgroud.selenium;

import com.github.eljaiek.autopilot.testng.AutopilotTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@AutopilotTest(modules = {WebDriverModule.class, GoogleWidgetsModule.class})
public class GooglePageTest {

    @Inject
    private WebDriver webDriver;

    @Inject
    private GooglePage googlePage;

    @AfterMethod
    public void tearDown() {
        webDriver.close();
    }

    @Test(dataProvider = "google-test")
    public void testGoogleSearch(String txt, String color) throws InterruptedException {
        googlePage.goTo();

        //change the color of the google page
        googlePage.execute("document.body.style.backgroundColor='" + color + "';");

        //do search and show results
        googlePage.getSearchWidget().searchFor(txt);
        googlePage.getResults().displayResult();
        assertThat(true).isTrue();

    }

    @DataProvider(name = "google-test")
    public static Object[][] getData() {
        return new Object[][] {
                {
                        "guru",
                        "blue"
                }, {
                "guice",
                "green"
        }
        };
    }
}
