package swaglabs.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import swaglabs.utilities.CustomSoftAssertions;
import swaglabs.utilities.ElementActions;
import swaglabs.utilities.Waits;

public class CheckoutPage extends BasePage {
    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(css = ".complete-header")
    private WebElement confirmationMessage;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    @FindBy(css = ".summary_total")
    private WebElement totalAmount;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        Waits.waitForElementVisible(driver, firstNameInput);
    }

    public void enterShippingInfo(String firstName, String lastName, String zip) {
        ElementActions.sendData(driver, firstNameInput, firstName);
        ElementActions.sendData(driver, lastNameInput, lastName);
        ElementActions.sendData(driver, postalCodeInput, zip);
        ((JavascriptExecutor) driver).executeScript("sauce:context=Entered shipping info");
    }

    public void proceedToOverview() {
        ElementActions.clickElement(driver, continueButton);
        Waits.waitForElementVisible(driver, totalAmount); // Wait for overview page [[3]]
    }

    public void completeCheckout() {
        ElementActions.clickElement(driver, finishButton);
        Waits.waitForElementVisible(driver, confirmationMessage); // Ensure completion [[6]]
    }

    public boolean isConfirmationDisplayed() {
        return ElementActions.isElementDisplayed(driver, confirmationMessage);
    }

    public String getErrorMessage() {
        return ElementActions.getText(driver, errorMessage);
    }

    public CheckoutPage validateErrorMessage(String expectedMessage) {
        CustomSoftAssertions.softAssertion.assertEquals(
                getErrorMessage(),
                expectedMessage,
                "Error message mismatch [[4]]"
        );
        return this;
    }

    public CheckoutPage validateTotalAmount(String expectedTotal) {
        CustomSoftAssertions.softAssertion.assertEquals(
                ElementActions.getText(driver, totalAmount),
                expectedTotal,
                "Total amount mismatch [[7]]"
        );
        return this;
    }
}