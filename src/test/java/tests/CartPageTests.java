package tests;

import org.testng.Assert;
import swaglabs.drivers.DriverManager;
import swaglabs.pages.CartPage;
import swaglabs.pages.CheckoutPage;
import swaglabs.pages.InventoryPage;
import swaglabs.pages.LoginPage;
import swaglabs.utilities.*;
import org.testng.annotations.*;
import java.util.Arrays;
import java.util.List;

public class CartPageTests extends BaseTest {
    private CartPage cartPage;
    private static final List<String> EXPECTED_NAMES =
            Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt");
    private static final List<String> EXPECTED_PRICES =
            Arrays.asList("$29.99", "$15.99");

    @BeforeMethod
    public void setupCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password"));
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addMultipleItemsToCart(2);
        driver.get(ConfigReader.getProperty("cart.url"));
        cartPage = new CartPage(driver);
    }

    @Test(description = "Verify multiple product details",priority = 1)
    public void testCartContents() {
        cartPage.validateAllProducts(EXPECTED_NAMES, EXPECTED_PRICES);
        CustomSoftAssertions.customAssertAll();
    }

    @Test(description = "Verify item removal functionality",priority = 2)
    public void testItemRemoval() {
        String productName = "Sauce Labs Backpack"; // Matches item added in setup
        cartPage.removeItem(productName);
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Item not removed");
    }

    @Test(description = "Verify empty cart state",priority = 3)
    public void testEmptyCart() {
        cartPage.removeAllItems();
        cartPage.validateEmptyCart();
        //CustomSoftAssertions.customAssertAll();
    }

@Test(description = "Verify Continue Shopping button navigates to Inventory Page", priority = 4)
public void testContinueShoppingNavigatesToInventoryPage() {
    // Step 1: Click the Continue Shopping button on the Cart Page
    cartPage.clickContinueShoppingButton();

    // Step 2: Get the current URL after redirection
    String currentUrl = driver.getCurrentUrl();

    // Step 3: Assert that the user is redirected to the Inventory Page
    Assert.assertTrue(currentUrl.contains("inventory.html"),
            "Navigation failed: URL does not contain 'inventory.html'. Actual URL: " + currentUrl);

}


    @Test(description = "Verify cart state persistence",priority = 5)
    public void testCartPersistence() {
        int initialCount = cartPage.getCartItemCount();

        // Proper session reset
        driver.quit();
        driver = DriverManager.createInstance("chrome");
        driver.get(ConfigReader.getProperty("app.url"));

        // Re-login and verify
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password"));

        driver.get(ConfigReader.getProperty("cart.url"));
        cartPage = new CartPage(driver);

        // Cart should be empty after session reset
        Assert.assertEquals(cartPage.getCartItemCount(), 0,
                "Cart should be empty after session reset");
    }
}