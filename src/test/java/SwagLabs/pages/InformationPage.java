package SwagLabs.pages;

import SwagLabs.utilities.PropertiesUtils;
import SwagLabs.utilities.Validations;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import SwagLabs.utilities.CustomSoftAssertions;
import SwagLabs.utilities.ElementActions;

public class InformationPage {
    //variables
    private WebDriver driver;

    //constructor
    public InformationPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");
    private final By emptyFirstNameErrorMSG = By.cssSelector("[data-test='error']");

    //actions
    @Step("Fill information form: first name, last name and postal code")
    public InformationPage fillInformationForm(String firstName, String lastName, String postalCode) {
        ElementActions.sendData(driver, this.firstName, firstName);
        ElementActions.sendData(driver, this.lastName, lastName);
        ElementActions.sendData(driver, this.postalCode, postalCode);
        return this;
    }

    public String getErrorMessage() {
        return ElementActions.getText(driver, emptyFirstNameErrorMSG);
    }

    @Step("Click continue button")
    public OverviewPage clickContinueButton() {
        ElementActions.clickElement(driver, continueButton);
        return new OverviewPage(driver);
    }

    @Step("Click cancel button")
    public CartPage clickCancelButton() {
        ElementActions.clickElement(driver, cancelButton);
        return new CartPage(driver);
    }

    public void assertInformationPage(String firstName, String lastName, String postalCode) {
        CustomSoftAssertions.softAssertion.assertEquals(ElementActions.getText(driver, this.firstName), firstName);
        CustomSoftAssertions.softAssertion.assertEquals(ElementActions.getText(driver, this.lastName), lastName);
        CustomSoftAssertions.softAssertion.assertEquals(ElementActions.getText(driver, this.postalCode), postalCode);
    }

    public InformationPage assertEmptyInformation(String expectedError, String actualError) {
        Validations.validateEquals(expectedError, actualError, "First name can not be empty");
        return this;
    }
}
