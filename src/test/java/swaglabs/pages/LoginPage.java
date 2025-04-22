package swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import swaglabs.utilities.ElementActions;
import swaglabs.utilities.Waits;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class LoginPage extends BasePage {
    // Locators with @FindBy
    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    // Constructor: Pass driver to BasePage
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        waitForElementVisible(usernameInput);
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        waitForElementVisible(passwordInput);
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        waitForElementClickable(loginButton);
        loginButton.click();
    }

    public InventoryPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new InventoryPage(driver); // Assumes InventoryPage exists
    }

//    public boolean isLoginFormDisplayed() {
//        return ElementActions.isElementDisplayed(driver, usernameInput) &&
//                ElementActions.isElementDisplayed(driver, passwordInput) &&
//                ElementActions.isElementDisplayed(driver, loginButton); // [[1]][[5]]
//    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public LoginPage verifyErrorMessage(String expectedMessage) {
        String actualMessage = errorMessage.getText().trim();
        assertTrue(actualMessage.contains(expectedMessage),
                String.format("Expected: %s\nActual: %s", expectedMessage, actualMessage));
        return this;
    }

    public LoginPage assertUnsuccessfulLogin() {
        Waits.waitForElementVisible(driver, errorMessage);
        assertFalse(errorMessage.getText().isEmpty(), "Error message not shown");
        return this;
    }
}