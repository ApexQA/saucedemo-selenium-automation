package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.CustomSoftAssertion;
import utilities.ElementActions;

public class InformationPage {
    //variables
    private WebDriver driver;

    //constructor
    public InformationPage(WebDriver driver){
        this.driver = driver;
    }

    //locators
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");

    //actions
    @Step("Fill information form: first name, last name and postal code")
    public InformationPage fillInformationForm(String firstName, String lastName, String postalCode){
        ElementActions.sendData(driver, this.firstName, firstName);
        ElementActions.sendData(driver, this.lastName, lastName);
        ElementActions.sendData(driver, this.postalCode, postalCode);
        return this;
    }

    @Step("Click continue button")
    public OverviewPage clickContinueButton(){
        ElementActions.clickElement(driver, continueButton);
        return new OverviewPage(driver);
    }

    public void assertInformationPage(String firstName, String lastName, String postalCode){
        CustomSoftAssertion.softAssertion.assertEquals(ElementActions.getTextFromInput(driver, this.firstName), firstName);
        CustomSoftAssertion.softAssertion.assertEquals(ElementActions.getTextFromInput(driver, this.lastName), lastName);
        CustomSoftAssertion.softAssertion.assertEquals(ElementActions.getTextFromInput(driver, this.postalCode), postalCode);
    }


}
