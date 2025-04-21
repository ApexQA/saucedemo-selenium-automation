package swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import swaglabs.utilities.ElementActions;
import swaglabs.utilities.Validations;

public class ConfirmationPage {
    //variables
    private WebDriver driver;

    //constructor
    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    @FindBy(css = "[data-test='complete-header']")
    private WebElement confirmationMessage;

    //actions
    //@Step("Get confirmation message")
    public String getConfrirmationMessage() {
        return ElementActions.getText(driver, confirmationMessage);
    }

    //validation
    //@Step("Assert Confirmation Message: {0}")
    public void assertConfirmationMessage(String expectedMessage) {
        String actualMessage = getConfrirmationMessage();
        Validations.validateEquals(actualMessage, expectedMessage, "Confirmation message mismatch");
    }
}
