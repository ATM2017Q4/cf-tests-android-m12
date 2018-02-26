package com.cf.ui.pages;

import com.cf.ui.util.WebDriverWaits;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
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

    @AndroidFindBy(id = "com.cf.flightsearch:id/searchImage")
    private AndroidElement submitButton;

    @AndroidFindAll(value = {@AndroidBy(id = "com.cf.flightsearch:id/month")})
    private List<AndroidElement> currentMonth;

    @AndroidFindBy(id = "com.cf.flightsearch:id/daysOfWeek")
    private AndroidElement daysOfWeek;

    private By animationView = By.id("com.cf.flightsearch:id/animationView");
    private By progressIndicator = By.id("com.cf.flightsearch:id/progressIndicator");

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
//        WebElement calendar = driver
//                .findElement(new MobileBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"" + month + "\"))"));
        Dimension size = driver.manage().window().getSize();
        int screenHeightCenter = (int) (size.getHeight() * 0.5);
        int screenWidthCenter = (int) (size.getWidth() * 0.5);

        TouchAction action = new TouchAction(driver);
        while (!(currentMonth.get(0).getText().equals(month) && currentMonth.get(0).getLocation().getY() < screenHeightCenter * 0.7)) {
            action.press(screenHeightCenter, screenWidthCenter).waitAction(Duration.ofSeconds(1)).moveTo(daysOfWeek).release().perform();
        }
        days.get(departureDay - 1).click();
        days.get(returnDay - 1).click();
        applyButton.click();
        return this;
    }

    public ResultsPage submitForm() {
        submitButton.click();
        WebDriverWaits.waitForInvisibility(driver, animationView, 10);
        WebDriverWaits.waitForInvisibility(driver, progressIndicator, 60);
        return new ResultsPage(driver);
    }
}
