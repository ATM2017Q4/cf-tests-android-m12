package com.cf.ui.tests;

import com.cf.ui.pages.LoadPage;
import com.cf.ui.pages.ResultsPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class CheapFlightsTest {
    public AppiumDriver driver;


    @BeforeEach
    public void setUp() {

        File app = null;
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "./src/test/resources/");
        try {
            app = new File(appDir.getCanonicalPath(), "com.cf.flightsearch.apk");
        } catch (IOException e) {
            e.printStackTrace();
        }
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("plarformVersion", "5.1.1");
        capabilities.setCapability("deviceName", "094749ef0de89c65");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.cf.flightsearch");
        try {
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchCheapFlight() {

        LoadPage loadPage = new LoadPage(driver);

        loadPage.closeSignInOverlay()
                .chooseOrigin("Vienna")
                .chooseDestination("London")
                .pickDates("Jun", 7, 17)
                .submitForm()
                .openFilters()
                .chooseNonStopFlights()
                .modifyLayoverDuration(4, 3)
                .applyFilters();
        Assertions.assertTrue(ResultsPage.getPrice(0) < 200);

    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }
}
