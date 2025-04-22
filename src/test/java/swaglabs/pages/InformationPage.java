package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import swaglabs.utilities.Validations;
import org.openqa.selenium.WebDriver;
import swaglabs.utilities.CustomSoftAssertions;
import swaglabs.utilities.ElementActions;

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
    //@Step("Fill information form: first name, last name and postal code")
    public InformationPage fillInformationForm(String firstName, String lastName, String postalCode) {
        ElementActions.sendDataBy(driver, this.firstName, firstName);
        ElementActions.sendDataBy(driver, this.lastName, lastName);
        ElementActions.sendDataBy(driver, this.postalCode, postalCode);
        return this;
    }

    public String getErrorMessage() {
        return ElementActions.getText(driver, emptyFirstNameErrorMSG);
    }

    //@Step("Click continue button")
    public OverviewPage clickContinueButton() {
        ElementActions.clickElementBy(driver, continueButton);
        return new OverviewPage(driver);
    }

    //@Step("Click cancel button")
    public CartPage clickCancelButton() {
        ElementActions.clickElementBy(driver, cancelButton);
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

