package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.BrowserActions;
import utilities.ElementActions;

public class LoginPage {
    //Variables
    private final WebDriver driver;

    // Locators
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");
//    private By usernameInput = By.id("user-name");
//    private By passwordInput = By.id("password");
//    private By loginButton = By.id("login-button");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Navigate to the login page
    public void navigateToLoginPage() {
        BrowserActions.navigateToURL(driver,"https://www.saucedemo.com/");
    }


    //Actions > wait - scroll - find - sendkeys
    public LoginPage enterUsername(String username) {

        ElementActions.sendData(driver, this.username, username);
        return this;
    }
    public LoginPage enterPassword(String password) {

        ElementActions.sendData(driver, this.password, password);
        return this;
    }
    public LoginPage clickLoginButton() {

        ElementActions.clickElement(driver, loginButton);
        return this;
    }
    public String getErrorMessage () {
        return ElementActions.getText(driver, errorMessage);
    }

    //Validations

    public LoginPage assertSuccessfulLogin () {
        Assert.assertEquals(BrowserActions.getCurrentURL(driver),"https://www.saucedemo.com/inventory.html");
        return this;
    }

    public LoginPage assertUnsuccessfulLogin() {
        Assert.assertEquals(getErrorMessage(),"Epic sadface: Username and password do not match any user in this service");
        return this;
    }


//    public void enterUsername(String username) {
//        driver.findElement(usernameInput).sendKeys(username);
//    }
//
//    public void enterPassword(String password) {
//        driver.findElement(passwordInput).sendKeys(password);
//    }
//
//    public void clickLogin() {
//        driver.findElement(loginButton).click();
//    }
}
