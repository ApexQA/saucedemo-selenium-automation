package SwagLabs.pages;

import SwagLabs.utilities.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // Locators
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage=By.cssSelector("[data-test='error']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
        //ElementActions.sendData(driver, this.usernameInput,username);
    }

    public void enterPassword(String password) {

        driver.findElement(passwordInput).sendKeys(password);
        //ElementActions.sendData(driver, this.passwordInput,password);
    }

    public void clickLogin() {

        driver.findElement(loginButton).click();
        //ElementActions.clickElement(driver,loginButton);
    }

    public String getErrorMessage(){
        return ElementActions.getText(driver,errorMessage);
    }
}
