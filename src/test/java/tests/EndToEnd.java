package tests;

import SwagLabs.Drivers.DriverManager;
import SwagLabs.pages.*;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import SwagLabs.utilities.BrowserActions;
import SwagLabs.utilities.ConfigReader;

public class EndToEnd {
    private WebDriver driver;
    String FIRST_NAME = ConfigReader.getProperty("information-form.firstName");
    String LAST_NAME = ConfigReader.getProperty("information-form.lastName");
    String POSTAL_CODE =  ConfigReader.getProperty("information-form.postalCode");

    // Test methods with proper dependencies
    @Test(priority = 1)
    public void successfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(ConfigReader.getProperty("valid.username"));
        loginPage.enterPassword(ConfigReader.getProperty("valid.password"));
        loginPage.clickLogin();
        String message = "URL is not as expected";
        String actualURL = ConfigReader.getProperty("home.url");
        String expectedURL = BrowserActions.getCurrentURL(driver);
        Assert.assertEquals(actualURL, expectedURL, message);
    }

    @Test(dependsOnMethods = "successfulLogin")
    public void addingProductToCart() {
       HomePage homePage = new HomePage(driver);
        homePage.addSpecificProductToCart(ConfigReader.getProperty("product.1.name"));
        homePage.assertProductAddedToCart(ConfigReader.getProperty("product.1.name"));
    }

    @Test(dependsOnMethods = "addingProductToCart")
    public void checkoutProduct() {
        new HomePage(driver).clickCartIcon()
                .assertProductDetails(ConfigReader.getProperty("product.1.name"),
                        ConfigReader.getProperty("product.1.price"));
    }

    @Test(dependsOnMethods = "checkoutProduct")
    public void fillInformationForm() {
        new CartPage(driver).clickCheckoutButton();
        InformationPage informationPage = new InformationPage(driver);

        informationPage.fillInformationForm(
                        FIRST_NAME,
                        LAST_NAME,
                        POSTAL_CODE);
        informationPage.assertInformationPage(
                        FIRST_NAME,
                        LAST_NAME,
                        POSTAL_CODE);
    }

    @Test(dependsOnMethods = "fillInformationForm")
    public void finishCheckout(){
        new InformationPage(driver).clickContinueButton();
        new OverviewPage(driver).clickOnFinishButton();
        new ConfirmationPage(driver).assertConfirmationMessage(ConfigReader.getProperty("confirmationMSG"));
    }

    @BeforeClass
    public void setup() {
        driver = DriverManager.createInstance("chrome");
        driver = DriverManager.getDriver();
        driver.get(ConfigReader.getProperty("base.url"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}