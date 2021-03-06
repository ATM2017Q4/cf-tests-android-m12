package com.cf.ui.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;

public class ResultsPage extends BasePage {

    @AndroidFindBy(id = "com.cf.flightsearch:id/filtersCard")
    private AndroidElement filtersButton;

    @AndroidFindBy(id = "com.cf.flightsearch:id/nonstop")
    private AndroidElement nonStopFlights;

    @AndroidFindBy(id = "com.cf.flightsearch:id/averagePriceLayout")
    private AndroidElement averagePriceLayout;

    @AndroidFindBy(id = "com.cf.flightsearch:id/toolbar")
    private AndroidElement toolbar;

    @AndroidFindBy(id = "com.cf.flightsearch:id/graph")
    private AndroidElement graph;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Duration\")")
    private AndroidElement duration;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.cf.flightsearch:id/layoverSlider\").instance(0)")
    private AndroidElement originLayoverDurationSlider;

    @AndroidFindBy(id = "com.cf.flightsearch:id/applyButton")
    private AndroidElement applyButton;

    @AndroidFindAll(value = {@AndroidBy(id = "com.cf.flightsearch:id/price")})
    private static List<AndroidElement> prices;

    private By durationXpath = By.xpath("//*[@resource-id=\"com.cf.flightsearch:id/durationRow\"]");

    public ResultsPage(AppiumDriver driver) {
        super(driver);
    }


    public ResultsPage openFilters() {
        logger.info("Opening the filters view.");
        filtersButton.click();
        return this;
    }

    public ResultsPage chooseNonStopFlights() {
        logger.info("Choosing non-stop flights only.");
        nonStopFlights.click();
        return this;
    }

    public ResultsPage modifyLayoverDuration(double multiplier) {
        TouchAction action = new TouchAction(driver);
        Dimension screenSize = driver.manage().window().getSize();
        int screenHeightCenter = (int) (screenSize.getHeight() * 0.6);
        int screenWidthCenter = (int) (screenSize.getWidth() * 0.6);
        logger.info("Scrolling the page till the Duration filter is visible.");
        while ((driver.findElements(durationXpath)).isEmpty()) {
            action.press(screenHeightCenter, screenWidthCenter).waitAction(Duration.ofSeconds(2)).moveTo(toolbar).release().perform();
        }
        logger.info("Opening the duration filter.");
        duration.click();
        Dimension size = originLayoverDurationSlider.getSize();
        int sliderWidth = size.getWidth();
        logger.info("Moving the layover slider.");
        action.press(originLayoverDurationSlider).moveTo((sliderWidth - (int) (sliderWidth * multiplier)), 0).release().perform();
        return this;
    }

    public ResultsPage applyFilters() {
        logger.info("Applying the chosen filters.");
        applyButton.click();
        return this;
    }

    public static int getPrice(int index) {
        String[] price = prices.get(index).getText().split("\\$");
        return Integer.parseInt(price[1]);

    }

}
