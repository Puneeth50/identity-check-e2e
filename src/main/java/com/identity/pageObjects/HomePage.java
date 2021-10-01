package com.identity.pageObjects;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private By enterRegTextBox = By.id("vrm-input");
    private By fullCheck = By.linkText("Get a Full Check");
    private By checkVehicle = By.xpath("//button[text()='Check Vehicle']");


    public void enterCarReg(String reg) {
        waitForExpectedElement(enterRegTextBox).clear();
        waitForExpectedElement(enterRegTextBox).sendKeys(reg);
    }

    public void clickFullCheck() {
        waitForExpectedElement(fullCheck).click();
    }

    public void clickCheckVehicle() {
        waitForExpectedElement(checkVehicle).click();
    }
}
