package swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import swaglabs.utilities.ElementActions;

public class OverviewPage {
    //variables
    private WebDriver driver;

    //locator
    @FindBy(id = "finish")
    private WebElement finishButton;

    //constructor
    public OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    //actions
    //@Step("Click on finish button")
    public ConfirmationPage clickOnFinishButton() {
        ElementActions.clickElement(driver, finishButton);
        return new ConfirmationPage(driver);
    }
}
