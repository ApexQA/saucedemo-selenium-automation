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
import pages.CheckoutPage;
import utilities.ConfigReader;

import java.time.Duration;

public class CheckoutTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Ensure GUI is visible
        // Remove headless argument if present:
        options.addArguments("--headless");
        driver = new ChromeDriver();
        driver.get(ConfigReader.getProperty("base.url"));
    }

    @Test(timeOut = 10000) // Test will fail if it takes longer than 10 seconds
    public void testValidCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.clickCheckoutButton();
        wait.until(ExpectedConditions.urlContains("/checkout-step-one.html"));
        System.out.println("Getting into checkout page is successful! :" + driver.getCurrentUrl());
        new WebDriverWait(driver, Duration.ofSeconds(5000));

        // Add assertions to verify login success
        String expectedUrl = "https://www.saucedemo.com/v1/checkout-step-one.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Login failed: URL mismatch");    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
