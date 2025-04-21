package SwagLabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import SwagLabs.utilities.ElementActions;
import io.qameta.allure.Step;

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
