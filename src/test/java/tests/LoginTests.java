package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import SwagLabs.pages.LoginPage;
import SwagLabs.utilities.BaseTest;
import SwagLabs.utilities.ConfigReader;

import java.time.Duration;

public class LoginTests  extends BaseTest {

    @Test(timeOut = 10000)
    public void testValidLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(ConfigReader.getProperty("valid.username"));
        loginPage.enterPassword(ConfigReader.getProperty("valid.password"));
        loginPage.clickLogin();

        // Wait for the URL to contain "/inventory.html"
        wait.until(ExpectedConditions.urlContains("/inventory.html"));
        System.out.println("Login successful! Current URL: " + driver.getCurrentUrl());

        // Add assertions to verify login success
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Login failed: URL mismatch");
    }
}