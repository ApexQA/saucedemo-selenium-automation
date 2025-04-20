package tests;

import SwagLabs.Drivers.DriverManager;
import SwagLabs.pages.CartPage;
import SwagLabs.utilites.ConfigReader;
import SwagLabs.utilites.CustomSoftAssertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartPageTests {

    private WebDriver driver;
    private CartPage cartPage;

    @BeforeMethod
    public void setup() {
        driver= DriverManager.createInstance("edge");
        driver = DriverManager.getDriver(); // Replace with your driver setup logic
        driver.get(ConfigReader.getProperty("cart.url")); // Adjust the URL as needed
        cartPage = new CartPage(driver);
    }

    @Test(description = "Verify product name and price on Cart Page")
    public void testProductDetails() {
        String expectedProductName = "Sauce Labs Backpack";
        String expectedProductPrice = "$29.99";

        cartPage.assertProductDetails(expectedProductName, expectedProductPrice);

        // Use this to trigger all soft assertions at the end
        CustomSoftAssertions.softAssertion.assertAll();
    }

    @Test(description = "Verify checkout button is clickable and redirects")
    public void testCheckoutButtonClick() {
        cartPage.clickCheckoutButton();

        // Optionally validate that URL or page title changed
        String currentUrl = driver.getCurrentUrl();
        org.testng.Assert.assertTrue(currentUrl.contains("checkout-step-one.html"),
                "User should be redirected to the Checkout Step One page after clicking Checkout.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
