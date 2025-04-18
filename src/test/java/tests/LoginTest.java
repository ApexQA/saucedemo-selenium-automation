package tests;

import drivers.DriverManager;
import listeners.TestNGListeners;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import utilities.BrowserActions;

@Listeners(TestNGListeners.class)
public class LoginTest {
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