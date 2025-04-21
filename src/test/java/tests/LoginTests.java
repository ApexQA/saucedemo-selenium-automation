package tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import swaglabs.pages.LoginPage;
import swaglabs.utilities.BaseTest;
import swaglabs.utilities.ConfigReader;

import java.time.Duration;

public class LoginTests extends BaseTest {
    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password")
        );

        // Verify successful login via URL [[4]]
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html",
                "Login failed: URL mismatch"
        );
    }

    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "invalid_password");

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Epic sadface: Username and password do not match"),
                "Error message not displayed"
        );
    }
}