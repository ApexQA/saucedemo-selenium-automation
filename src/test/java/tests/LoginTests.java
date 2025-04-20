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
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        // Initialize the class-level 'driver' variable
        driver = new ChromeDriver(options);
        System.out.println("Driver initialized: " + (driver != null)); // Debugging log

        driver.get(ConfigReader.getProperty("base.url"));
    }

    @Test(timeOut = 10000) // Test will fail if it takes longer than 10 seconds
    public void testValidLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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

    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}