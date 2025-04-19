package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementActions;

import java.util.concurrent.BlockingDeque;

public class OverviewPage {
    //variables
    private WebDriver driver;

    //locator
    private final By finishButton = By.id("finish");

    //constructor
    public OverviewPage(WebDriver driver){
        this.driver = driver;
    }

    //actions
    @Step("Click on finish button")
    public ConfirmationPage clickOnFinishButton(){
        ElementActions.clickElement(driver, finishButton);
        return new ConfirmationPage(driver);
    }
}
