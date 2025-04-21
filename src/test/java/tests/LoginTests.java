package tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import swaglabs.pages.LoginPage;
import swaglabs.utilities.BaseTest;
import swaglabs.utilities.ConfigReader;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTest {
    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password")
        );

        // Verify successful login via URL
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

        assertTrue(
                loginPage.getErrorMessage().contains("Epic sadface: Username and password do not match"),
                "Error message not displayed"
        );
    }
    @Test
    public void testInvalidUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", ConfigReader.getProperty("valid.password")); // [[5]]

        String errorMessage = loginPage.getErrorMessage();
        assertTrue(errorMessage.contains("Epic sadface: Username and password do not match"),
                "Unexpected error message: " + errorMessage); // [[2]][[8]]
    }

    @Test
    public void testInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("valid.username"), "wrong_password"); // [[5]]

        String errorMessage = loginPage.getErrorMessage();
        assertTrue(errorMessage.contains("Epic sadface: Username and password do not match"),
                "Unexpected error message: " + errorMessage);
    }

    @Test
    public void testEmptyFields() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", ""); // Test empty credentials [[1]]

        String errorMessage = loginPage.getErrorMessage();
        assertTrue(errorMessage.contains("Epic sadface: Username is required"),
                "Unexpected error message: " + errorMessage);
    }

    @Test
    public void testLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", ConfigReader.getProperty("valid.password")); // [[8]]

        String errorMessage = loginPage.getErrorMessage();
        assertTrue(errorMessage.contains("Epic sadface: Sorry, this user has been locked out"),
                "Unexpected error message: " + errorMessage);
    }
}