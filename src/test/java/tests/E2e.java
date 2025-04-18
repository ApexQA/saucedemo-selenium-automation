package tests;

import drivers.DriverManager;
import listeners.TestNGListeners;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.BrowserActions;

@Listeners(TestNGListeners.class)
public class E2e {
    //Variables
    private WebDriver driver;

    // Tests
    @Test
    public void successfulLogin() {
        new LoginPage(driver).enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLogin()
                .assertSuccessfulLogin();
    }

    //Configurations
    @BeforeMethod
    public void setup() {
        driver = DriverManager.createInstance();
        new LoginPage(driver).navigateToLoginPage();
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        BrowserActions.closeBrowser(DriverManager.getDriver());
        //CustomSoftAssertion.customAssertAll();
    }
}