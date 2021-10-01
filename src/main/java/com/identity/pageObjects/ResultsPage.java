package com.identity.pageObjects;

import org.openqa.selenium.By;

public class ResultsPage extends BasePage {

    private By registrationText = By.xpath("//dt[contains(text(),'Registration')]/following-sibling::dd");
    private By vehicleModelText = By.xpath("//dt[text()='Vehicle']/following-sibling::dd");
    private By yearText = By.xpath("//dt[text()='Year']/following-sibling::dd");
    private By colourText = By.xpath("//dt[text()='Colour']/following-sibling::dd");
    private By tryAgainLink = By.linkText("Try Again");
    private By carTaxCheckIcon = By.xpath("//img[@alt='Car Tax Check']");

    public String getRegistration() {
        return waitForExpectedElement(registrationText).getText();
    }

    public String getModel() {
        return waitForExpectedElement(vehicleModelText).getText();
    }

    public String getYear() {
        return waitForExpectedElement(yearText).getText();
    }

    public String getColour() {
        return waitForExpectedElement(colourText).getText();
    }

    public void clickTryAgain(){
        waitForExpectedElement(tryAgainLink).click();
    }

    public void clickCarTaxCheckIcon(){
        waitForExpectedElement(carTaxCheckIcon).click();
    }

}
