package com.cf.ui.pages;

import com.cf.ui.util.WebDriverWaits;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {

    public SearchPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.cf.flightsearch:id/originCell")
    private MobileElement origin;

    @AndroidFindBy(id = "com.cf.flightsearch:id/smartySearchText")
    private MobileElement originField;

    @AndroidFindBy(id = "com.cf.flightsearch:id/destinationCell")
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
    private ArrayList<AndroidElement> currentMonth;

    @AndroidFindBy(id = "com.cf.flightsearch:id/daysOfWeek")
    private AndroidElement daysOfWeek;

    private By animationView = By.id("com.cf.flightsearch:id/animationView");
    private By progressIndicator = By.id("com.cf.flightsearch:id/progressIndicator");

    public SearchPage chooseOrigin(String originName) {
        logger.info("Clicking the origin cell");
        origin.click();
        logger.info("Waiting for the search view to appear and entering the origin name.");
        WebDriverWaits.waitForVisibility(driver, originField, 10).sendKeys(originName);
        logger.info("Choosing all airports.");
        WebDriverWaits.waitForVisibility(driver, allAirports, 10).click();
        return this;
    }

    public SearchPage chooseDestination(String destinationName) {
        logger.info("Clicking the destination cell");
        destination.click();
        logger.info("Waiting for the search view to appear and entering the destination name.");
        WebDriverWaits.waitForVisibility(driver, destinationField, 10).sendKeys(destinationName);
        logger.info("Choosing all airports.");
        WebDriverWaits.waitForVisibility(driver, allAirports, 10).click();
        return this;
    }

    public SearchPage pickDates(String month, int departureDay, int returnDay) {
        logger.info("Opening the date picker");
        datePicker.click();
        Dimension size = driver.manage().window().getSize();
        int screenHeightCenter = (int) (size.getHeight() * 0.5);
        int screenWidthCenter = (int) (size.getWidth() * 0.5);
        TouchAction action = new TouchAction(driver);

        logger.info("Scrolling the page to the needed month");
        while (!(currentMonth.get(0).getText().equals(month) && currentMonth.get(0).getLocation().getY() < screenHeightCenter * 0.7)) {
            action.press(screenHeightCenter, screenWidthCenter).waitAction(Duration.ofSeconds(1)).moveTo(daysOfWeek).release().perform();
        }

        //The mechanism is flawed enough but if (on this particular device)
        //the month names is positioned so that there are no relative layout elements before it
        //then the days cells for this particular month start from index 0 in the ArrayList
        days.get(departureDay - 1).click();
        days.get(returnDay - 1).click();

        logger.info("Applying the chosen dates");
        applyButton.click();
        return this;
    }

    public ResultsPage submitForm() {
        logger.info("Submitting the form with chosen values for origin , destination and dates.");
        submitButton.click();
        logger.info("Waiting till the transition animation disappearance.");
        WebDriverWaits.waitForInvisibility(driver, animationView, 10);
        logger.info("Waiting till the ResultsPage completely loads.");
        WebDriverWaits.waitForInvisibility(driver, progressIndicator, 60);
        return new ResultsPage(driver);
    }
}
