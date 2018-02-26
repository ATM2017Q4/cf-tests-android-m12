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
        capabilities.setCapability("deviceName", "device");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.cf.flightsearch");
        try {
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchCheapFlight() throws InterruptedException {
//        while (driver.findElementsById("com.cf.flightsearch:id/closeBtn").isEmpty()) {
//            try {
//                driver.findElementsById("com.cf.flightsearch:id/closeBtn").get(0);
//            } catch (java.lang.IndexOutOfBoundsException e) {
//                System.out.println("Overlay hasn't appeared yet");
//            }
//        }
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
        Assertions.assertTrue(ResultsPage.getPrice(0)<200);


//        new FluentWait(driver).withTimeout(10, TimeUnit.SECONDS).ignoring(org.openqa.selenium.NoSuchElementException.class).pollingEvery(1, TimeUnit.SECONDS).until(ExpectedConditions.presenceOfElementLocated(By.id("com.cf.flightsearch:id/closeBtn")));
//        driver.findElementById("com.cf.flightsearch:id/closeBtn").click();
//        driver.findElementById("com.cf.flightsearch:id/originCode").click();
////        driver.findElementById("com.cf.flightsearch:id/text").click();
//        new FluentWait(driver).withTimeout(10, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS).ignoring(org.openqa.selenium.NoSuchElementException.class).until(ExpectedConditions.visibilityOf(driver.findElementById("com.cf.flightsearch:id/smartySearchText")));
////        new TouchAction(driver).tap(driver.findElementById("com.cf.flightsearch:id/smartySearchText"));
//        driver.findElementById("com.cf.flightsearch:id/smartySearchText").sendKeys("Vienna");
//        new FluentWait(driver).withTimeout(10, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(driver.findElement(new MobileBy.ByAndroidUIAutomator("className(android.widget.LinearLayout).instance(4)"))));
//        driver.findElement(new MobileBy.ByAndroidUIAutomator("className(android.widget.LinearLayout).instance(4)")).click();
//        driver.findElementById("com.cf.flightsearch:id/destinationCode").click();
//        driver.findElementById("com.cf.flightsearch:id/smartySearchText").sendKeys("London");
//        new FluentWait(driver).withTimeout(10, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(driver.findElement(new MobileBy.ByAndroidUIAutomator("className(android.widget.LinearLayout).instance(4)"))));
//        driver.findElement(new MobileBy.ByAndroidUIAutomator("className(android.widget.LinearLayout).instance(4)")).click();
//        driver.findElementById("com.cf.flightsearch:id/dates").click();
//        Thread.sleep(5000);
//        new FluentWait(driver).withTimeout(10, TimeUnit.SECONDS)
//                .pollingEvery(1, TimeUnit.SECONDS)
//                .until(ExpectedConditions.visibilityOf(driver.findElement(new MobileBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"May\"))"))));
//        driver.findElements(new MobileBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.RelativeLayout\")")).get(15).click();
//        driver.findElements(new MobileBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.RelativeLayout\")")).get(25).click();
//        driver.findElementById("com.cf.flightsearch:id/applyButton").click();
//        driver.findElementById("com.cf.flightsearch:id/searchImage").click();
//        new FluentWait(driver).withTimeout(60, TimeUnit.SECONDS)
//                .pollingEvery(1, TimeUnit.SECONDS)
//                .ignoring(org.openqa.selenium.NoSuchElementException.class)
//                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("com.cf.flightsearch:id/animationView")));
//        new FluentWait(driver).withTimeout(60, TimeUnit.SECONDS)
//                .pollingEvery(1, TimeUnit.SECONDS)
//                .ignoring(org.openqa.selenium.NoSuchElementException.class)
//                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("com.cf.flightsearch:id/progressIndicator")));
//        driver.findElementById("com.cf.flightsearch:id/filtersCard").click();
//        driver.findElementById("com.cf.flightsearch:id/oneStop").click();
////        driver.findElement(new MobileBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.cf.flightsearch:id/scrollRoot\")).setAsVerticalList().scrollIntoView(new UiSelector().textContains(\"Duration\"))"));
//        TouchAction action = new TouchAction(driver);
//        action.press(driver.findElementById("com.cf.flightsearch:id/averagePriceLayout")).waitAction(Duration.ofSeconds(2)).moveTo(driver.findElementById("com.cf.flightsearch:id/toolbar")).release().perform();
//        action.press(driver.findElementById("com.cf.flightsearch:id/graph")).waitAction(Duration.ofSeconds(2)).moveTo(driver.findElementById("com.cf.flightsearch:id/toolbar")).release().perform();
//        Thread.sleep(5000);
//        driver.findElement(new MobileBy.ByAndroidUIAutomator("new UiSelector().textContains(\"Duration\")")).click();
//        MobileElement slider = driver.findElement(new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"com.cf.flightsearch:id/layoverSlider\").instance(0)"));
//        Dimension size = slider.getSize();
//        int sliderWidth = size.getWidth();
//        action.press(slider).moveTo((sliderWidth / 4), 0).release().perform();
//        driver.findElementById("com.cf.flightsearch:id/applyButton").click();
//        String[] price = driver.findElementsById("com.cf.flightsearch:id/price").get(0).getText().split("\\$");
//        Assertions.assertTrue(Integer.parseInt(price[1]) < 200);

    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }
}
