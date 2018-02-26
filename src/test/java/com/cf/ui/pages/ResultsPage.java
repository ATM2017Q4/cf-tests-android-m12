package com.cf.ui.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
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
    private List<AndroidElement> prices;

    public ResultsPage(AppiumDriver driver) {
        super(driver);
    }


    public ResultsPage openFilters() {
        filtersButton.click();
        return this;
    }

    public ResultsPage chooseNonStopFlights() {
        nonStopFlights.click();
        return this;
    }

    public ResultsPage modifyLayoverDuration(int divider, int multiplier) {
        TouchAction action = new TouchAction(driver);
        action.press(averagePriceLayout).waitAction(Duration.ofSeconds(2)).moveTo(toolbar).release().perform();
        action.press(graph).waitAction().waitAction(Duration.ofSeconds(2)).moveTo(toolbar).release().perform();
        duration.click();
        Dimension size = originLayoverDurationSlider.getSize();
        int sliderWidth = size.getWidth();
        action.press(originLayoverDurationSlider).moveTo((sliderWidth / divider), 0).release().perform();
        return this;
    }

    public ResultsPage applyFilters() {
        applyButton.click();
        return this;
    }

    public int getPrice(int index) {
        String[] price = prices.get(index).getText().split("\\$");
        return Integer.parseInt(price[1]);

    }

}