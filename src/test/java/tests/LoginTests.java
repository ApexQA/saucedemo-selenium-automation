package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.ConfigReader;

public class LoginTests {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get(ConfigReader.getProperty("base.url")); // Read URL from config
    }

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(ConfigReader.getProperty("valid.username"));
        loginPage.enterPassword(ConfigReader.getProperty("valid.password"));
        loginPage.clickLogin();
        // Add assertions (e.g., verify URL after login)
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
