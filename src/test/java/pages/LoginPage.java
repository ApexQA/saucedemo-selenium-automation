package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.BrowserActions;
import utilities.CustomSoftAssertion;
import utilities.ElementActions;
import utilities.Validations;

import static utilities.PropertiesUtils.getPropertyValue;

public class LoginPage {
    //Variables
    private final WebDriver driver;

    // Locators
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Navigate to the login page
    @Step("Navigating the login page")
    public void navigateToLoginPage(){
       BrowserActions.navigateToURL(driver, "https://www.saucedemo.com/");
    }

    // Actions
    @Step("Enter username: {0}")
    public LoginPage enterUsername(String username) {
        ElementActions.sendData(driver, this.username, username);
        return this;
    }

    @Step("Enter password: {0}")
    public LoginPage enterPassword(String password) {
        ElementActions.sendData(driver, this.password, password);
        return this;
    }

    @Step("Click on login button")
    public LoginPage clickLogin() {
        ElementActions.clickElement(driver, loginButton);
        return this;
    }

    //Getters
    @Step("Get error message")
    public String getErrorMessage(){
        return ElementActions.getText(driver, errorMessage);
    }

    //Validations
    @Step("Assert login page URL")
    public LoginPage assertLoginPageURL(){
        String message = "URL is not as expected";
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getCurrentURL(driver), getPropertyValue("homeURL"), message);
        return this;
    }

    @Step("Assert login page title")
    public LoginPage assertLoginPageTitle(){
        String message = "Title is not as expected";
        CustomSoftAssertion.softAssertion.assertEquals(BrowserActions.getPageTitle(driver), getPropertyValue("appTitle"), message);
        return this;
    }

    @Step("Assert soft successful login")
    public LoginPage assertSuccessfulLoginSoft(){
        assertLoginPageURL().assertLoginPageTitle();
        return this;
    }

    @Step("Assert successful login")
    public LoginPage assertSuccessfulLogin(){
        Validations.validatePageURL(driver, getPropertyValue("homeURL"));
        return this;
    }

    @Step("Assert unsuccessful login")
    public LoginPage assertUnsuccessfulLogin(){
        Validations.validateEquals(getErrorMessage(), getPropertyValue("errorMSG"), "Error message is not expected");
        return this;
    }
}
