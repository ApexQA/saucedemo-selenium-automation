// CheckoutPage.java
package swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import swaglabs.utilities.ElementActions;

import java.util.List;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement zipCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(className = "summary_info")
    private List<WebElement> summaryItems;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Input Actions
    public CheckoutPage enterFirstName(String name) {
        ElementActions.sendData(driver, firstNameInput, name);
        return this;
    }

    public CheckoutPage enterLastName(String name) {
        ElementActions.sendData(driver, lastNameInput, name);
        return this;
    }

    public CheckoutPage enterZipCode(String zip) {
        ElementActions.sendData(driver, zipCodeInput, zip);
        return this;
    }

    public void clickContinue() {
        ElementActions.clickElement(driver, continueButton);
    }

//    public void clickCancel() {
//        ElementActions.clickElement(driver, cancelButton);
//    }

    // Getters
    public String getErrorMessage() {
        return ElementActions.getText(driver, errorMessage);
    }

    public String getTotalAmount() {
        return ElementActions.getText(driver, totalLabel);
    }

    public List<WebElement> getSummaryItems() {
        return summaryItems;
    }
}