package tests;

import drivers.DriverManager;
import listeners.TestNGListeners;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.CartPage;
import pages.HomePage;
import pages.InformationPage;
import pages.LoginPage;
import utilities.BrowserActions;
import utilities.ConfigReader;

@Listeners(TestNGListeners.class)
public class E2e {
    private WebDriver driver;

    // Test methods with proper dependencies
    @Test(priority = 1)
    public void successfulLogin() {
        new LoginPage(driver).enterUsername(ConfigReader.getProperty("valid.username"))
                .enterPassword(ConfigReader.getProperty("valid.password"))
                .clickLogin()
                .assertSuccessfulLogin();
    }

    @Test(dependsOnMethods = "successfulLogin")
    public void addingProductToCart() {
        HomePage homePage = new HomePage(driver)
                .addSpecificProductToCart(ConfigReader.getProperty("product.1.name"))
                .assertProductAddedToCart(ConfigReader.getProperty("product.1.name"));
    }

    @Test(dependsOnMethods = "addingProductToCart")
    public void checkoutProduct() {
        new HomePage(driver).clickCartIcon()
                .assertProductDetails(ConfigReader.getProperty("product.1.name"),
                        ConfigReader.getProperty("product.1.price"));
    }

    @Test(dependsOnMethods = "checkoutProduct")
    public void fillInformationForm() {
        new CartPage(driver).clickCheckoutButton()
                .fillInformationForm(
                        ConfigReader.getProperty("information-form.firstName"),
                        ConfigReader.getProperty("information-form.lastName"),
                        ConfigReader.getProperty("information-form.postalCode"))
                .assertInformationPage(
                        ConfigReader.getProperty("information-form.firstName"),
                        ConfigReader.getProperty("information-form.lastName"),
                        ConfigReader.getProperty("information-form.postalCode"));
    }

    @Test(dependsOnMethods = "fillInformationForm")
    public void finishCheckout(){
        new InformationPage(driver)
                .clickContinueButton()
                .clickOnFinishButton()
                .assertConfirmationMessage(ConfigReader.getProperty("confirmationMSG"));
    }
    @BeforeClass
    public void setup() {
        driver = DriverManager.createInstance();
        new LoginPage(driver).navigateToLoginPage();
    }

    @AfterClass
    public void tearDown() {
        BrowserActions.closeBrowser(DriverManager.getDriver());
    }
}