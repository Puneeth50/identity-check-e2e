package com.identity.pageObjects;

import com.identity.helper.DriverHelper;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    private static final long DRIVER_WAIT_TIME = 10;

    @Getter
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverHelper.getWebDriver();
        this.wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);

    }

    /**
     * Loads a new web page in the current browser window
     **/
    public void startPage() throws InterruptedException {
        DriverHelper.loadPage();
    }


    public String getPageTitle(){
       return DriverHelper.getWebDriver().getTitle();
    }

    /**
     * Find the dynamic element wait until its visible
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    protected WebElement waitForExpectedElement(final By by) {
        return wait.until(visibilityOfElementLocated(by));
    }

    /**
     * Wait for element until custom xpath is visible
     *
     * @param xpathExpression Element location found by xpath
     **/
    protected WebElement getCustomXpathElement(String xpathExpression) {
        By xpath = By.xpath(xpathExpression);
        return waitForExpectedElement(xpath, 20);
    }

    /**
     * Find the dynamic element wait until its visible for a specified time
     *
     * @param by                Element location found by css, xpath, id etc...
     * @param waitTimeInSeconds max time to wait until element is visible
     **/
    public WebElement waitForExpectedElement(final By by, long waitTimeInSeconds) {
        try {
            wait = new WebDriverWait(driver, waitTimeInSeconds);
            return wait.until(visibilityOfElementLocated(by));
        } catch (NoSuchElementException e) {
            LOGGER.info(e.getMessage());
            return null;
        } catch (TimeoutException e) {
            LOGGER.info(e.getMessage());
            return null;
        }

    }

    /**
     * Find the dynamic elements wait until its visible
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    public List<WebElement> getElementList(final By by) {
        return wait.until(visibilityOfAllElementsLocatedBy(by));
    }

    /**
     * Is this element displayed or not?
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    public boolean isDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Select element by visible text
     *
     * @param by   Element location found by css, xpath, id etc...
     * @param text visible text to select from dropdown
     **/
    public void selectElement(By by, String text) {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }
}
