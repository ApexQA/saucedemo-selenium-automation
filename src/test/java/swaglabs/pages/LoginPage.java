package swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import swaglabs.utilities.ElementActions;

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
}