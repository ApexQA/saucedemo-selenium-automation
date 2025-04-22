package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import swaglabs.utilities.ElementActions;

public class OverviewPage {
    //variables
    private WebDriver driver;

    //locator
    private final By finishButton = By.id("finish");

    //constructor
    public OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    //actions
    //@Step("Click on finish button")
    public ConfirmationPage clickOnFinishButton() {
        ElementActions.clickElementBy(driver, finishButton);
        return new ConfirmationPage(driver);
    }
}
