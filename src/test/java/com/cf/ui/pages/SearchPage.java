package com.cf.ui.pages;

import com.cf.ui.util.WebDriverWaits;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends BasePage {

    public SearchPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.cf.flightsearch:id/originCode")
    private MobileElement origin;

    @AndroidFindBy(id = "com.cf.flightsearch:id/smartySearchText")
    private MobileElement originField;

    @AndroidFindBy(id = "com.cf.flightsearch:id/destinationCode")
    private MobileElement destination;

    @AndroidFindBy(id = "com.cf.flightsearch:id/smartySearchText")
    private MobileElement destinationField;

    @AndroidFindBy(id = "com.cf.flightsearch:id/dates")
    private MobileElement datePicker;

    @AndroidFindAll(value = {@AndroidBy(uiAutomator = "new UiSelector().className(\"android.widget.RelativeLayout\")")})
    private List<AndroidElement> days;

    @AndroidFindBy(uiAutomator = "className(android.widget.LinearLayout).instance(4)")
    private AndroidElement allAirports;

    @AndroidFindBy(id = "com.cf.flightsearch:id/applyButton")
    private AndroidElement applyButton;


    public SearchPage chooseOrigin(String originName) {
        origin.click();
        WebDriverWaits.waitForVisibility(driver, originField, 10).sendKeys(originName);
        WebDriverWaits.waitForVisibility(driver, allAirports, 10).click();
        return this;
    }

    public SearchPage chooseDestination(String destinationName) {
        destination.click();
        WebDriverWaits.waitForVisibility(driver, destinationField, 10).sendKeys(destinationName);
        WebDriverWaits.waitForVisibility(driver, allAirports, 10).click();
        return this;
    }

    public SearchPage pickDates(String month, int departureDay, int returnDay) {
        datePicker.click();
        WebElement calendar = driver
                .findElement(new MobileBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"" + month + "\"))"));
        WebDriverWaits.waitForVisibility(driver, calendar, 10);
        days.get(departureDay + 7).click();
        days.get(returnDay + 7).click();
        return this;
    }

    public SearchPage submitForm() {
        applyButton.click();
        return this;
    }
}
