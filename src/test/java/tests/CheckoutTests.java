package tests;

import SwagLabs.Drivers.DriverManager;
import SwagLabs.pages.*;
import SwagLabs.utilities.BrowserActions;
import SwagLabs.utilities.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckoutTests {
    private WebDriver driver;
    String FIRST_NAME = ConfigReader.getProperty("information-form.firstName");
    String LAST_NAME = ConfigReader.getProperty("information-form.lastName");
    String POSTAL_CODE = ConfigReader.getProperty("information-form.postalCode");

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
    public void emptyFirtsNameValidation(){
        new CartPage(driver).clickCheckoutButton();
        InformationPage informationPage = new InformationPage(driver);
        informationPage.clickContinueButton();
        String excpectedError = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        String actualError = ConfigReader.getProperty("emptyFirstNameErrorMSG");
        informationPage.assertEmptyInformation(excpectedError, actualError);
    }

    @Test(dependsOnMethods = "emptyFirtsNameValidation")
    public void emptyLastNameValidation(){
        InformationPage informationPage = new InformationPage(driver);
        informationPage.fillInformationForm(
                FIRST_NAME,
                "",
                POSTAL_CODE);
        informationPage.clickContinueButton();
        String excpectedError = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        String actualError = ConfigReader.getProperty("emptyLastNameErrorMSG");
        informationPage.assertEmptyInformation(excpectedError, actualError);
    }

    @Test(dependsOnMethods = "emptyLastNameValidation")
    public void emptyPostalCodeValidation(){
        InformationPage informationPage = new InformationPage(driver);
        informationPage.fillInformationForm(
                FIRST_NAME,
                LAST_NAME,
                "");
        informationPage.clickContinueButton();
        String excpectedError = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        String actualError = ConfigReader.getProperty("emptyPostalCodeErrorMSG");
        informationPage.assertEmptyInformation(excpectedError, actualError);
    }

    @Test(dependsOnMethods = "emptyPostalCodeValidation")
    public void fillInformationForm() {
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
    public void finishCheckout() {
        new InformationPage(driver).clickContinueButton();
        new OverviewPage(driver).clickOnFinishButton();
        new ConfirmationPage(driver).assertConfirmationMessage(ConfigReader.getProperty("confirmationMSG"));
    }

/*    @Test(dependsOnMethods = "fillInformationForm")
    public void cancelCheckout() {
        new InformationPage(driver).clickCancelButton();
        String message = "URL is not as expected";
        String actualURL = ConfigReader.getProperty("cartURL");
        String expectedURL = BrowserActions.getCurrentURL(driver);
        Assert.assertEquals(actualURL, expectedURL, message);
    }*/

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
