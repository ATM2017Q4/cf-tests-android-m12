package com.cf.ui.pages;

import com.cf.ui.util.WebDriverWaits;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;


public class LoadPage extends BasePage {

    public LoadPage(AppiumDriver driver) {
        super(driver);
    }

    private By overlayCloseButton = By.id("com.cf.flightsearch:id/closeBtn");

    public SearchPage closeSignInOverlay() {
        logger.info("Waiting for the Sign-in overlay to appear.");
        WebDriverWaits.waitForPresence(driver, overlayCloseButton, 10);
        logger.info("Closing the overlay.");
        driver.findElement(overlayCloseButton).click();
        return new SearchPage(driver);
    }
}
