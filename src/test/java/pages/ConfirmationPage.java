package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementActions;
import utilities.Validations;

public class ConfirmationPage {
    //variables
    private WebDriver driver;

    //constructor
    public ConfirmationPage(WebDriver driver){
        this.driver = driver;
    }

    //locators
    private final By confirmationMessage = By.cssSelector("complete-header");

    //actions
    @Step("Get confirmation message")
    public String getConfrirmationMessage(){
        return ElementActions.getText(driver, confirmationMessage);
    }

    //validation
    @Step("Assert Confirmation Message: {0}")
    public void assertConfirmationMessage(String expectedMessage){
        String actualMessage = getConfrirmationMessage();
        Validations.validateEquals(actualMessage, expectedMessage, "Confirmation message mismatch");
    }

}
