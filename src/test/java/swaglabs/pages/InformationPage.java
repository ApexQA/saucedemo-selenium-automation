package swaglabs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import swaglabs.utilities.Validations;
import org.openqa.selenium.WebDriver;
import swaglabs.utilities.CustomSoftAssertions;
import swaglabs.utilities.ElementActions;

//import io.qameta.allure.Step;

public class InformationPage {
    //variables
    private WebDriver driver;

    //constructor
    public InformationPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(css = "[data-test='error']")
    private WebElement emptyFirstNameErrorMSG;

    //actions
    //@Step("Fill information form: first name, last name and postal code")
    public InformationPage fillInformationForm(String firstName, String lastName, String postalCode) {
        ElementActions.sendData(driver, this.firstName, firstName);
        ElementActions.sendData(driver, this.lastName, lastName);
        ElementActions.sendData(driver, this.postalCode, postalCode);
        return this;
    }

    public String getErrorMessage() {
        return ElementActions.getText(driver, emptyFirstNameErrorMSG);
    }

    //@Step("Click continue button")
    public OverviewPage clickContinueButton() {
        ElementActions.clickElement(driver, continueButton);
        return new OverviewPage(driver);
    }

    //@Step("Click cancel button")
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

