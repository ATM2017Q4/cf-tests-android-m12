package com.cf.ui.util;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class WebDriverWaits {

    public static WebElement waitForPresence(AppiumDriver driver, By by, int timeOut) {
        Wait<AppiumDriver> wait = new FluentWait(driver).withTimeout(timeOut, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(org.openqa.selenium.NoSuchElementException.class);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return element;
    }

    public static WebElement waitForVisibility(AppiumDriver driver, WebElement element, int timeOut) {
        Wait<AppiumDriver> wait = new FluentWait(driver).withTimeout(timeOut, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(org.openqa.selenium.NoSuchElementException.class);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static Boolean waitForInvisibility(AppiumDriver driver, By by, int timeOut){
        Wait<AppiumDriver> wait = new FluentWait(driver).withTimeout(timeOut, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(org.openqa.selenium.NoSuchElementException.class);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
