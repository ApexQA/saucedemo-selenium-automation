package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.ConfigReader;

import java.time.Duration;

public class LoginTests {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Ensure GUI is visible
        options.addArguments("--disable-dev-shm-usage"); // Avoid shared memory issues
        options.addArguments("--no-sandbox"); // Required for CI environments
        options.addArguments("--remote-allow-origins=*"); // Fix for Chrome 111+

        driver = new ChromeDriver(options);
        driver.get(ConfigReader.getProperty("base.url"));
    }

    @Test(timeOut = 10000) // Test will fail if it takes longer than 10 seconds
    public void testValidLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(ConfigReader.getProperty("valid.username"));
        loginPage.enterPassword(ConfigReader.getProperty("valid.password"));
        loginPage.clickLogin();
        wait.until(ExpectedConditions.urlContains("/inventory.html"));
        System.out.println("Login successful! :" + driver.getCurrentUrl());
        new WebDriverWait(driver, Duration.ofSeconds(5000));

        // Add assertions to verify login success
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Login failed: URL mismatch");    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        //driver.quit();
    }
}
