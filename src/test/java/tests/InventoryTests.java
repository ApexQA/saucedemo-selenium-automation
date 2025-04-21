package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import swaglabs.pages.InventoryPage;
import swaglabs.pages.LoginPage;
import swaglabs.utilities.BaseTest;
import swaglabs.utilities.ConfigReader;
import swaglabs.utilities.ElementActions;
import swaglabs.utilities.Waits;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryTests extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setupTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password")
        );
        inventoryPage = new InventoryPage(driver);
    }

    @Test(priority = 1)
    public void testInventoryPageTitle() {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Swag Labs", "Page title does not match!");
    }

    @Test(priority = 2)
    public void testAddItemToCart() {
        inventoryPage.addFirstItemToCart();
        WebElement cartBadge = inventoryPage.getCartBadge();
        Waits.waitForElementVisible(driver, cartBadge);
        Assert.assertTrue(cartBadge.isDisplayed(), "Item was not added to cart!");
    }

    @Test(priority = 3)
    public void testOpenShoppingCart() {
        inventoryPage.addFirstItemToCart();
        inventoryPage.openShoppingCart();
        Assert.assertTrue(driver.getCurrentUrl().contains("/cart.html"), "Cart page did not open!");
    }

    @Test(priority = 4)
    public void testItemDetailsDisplayed() {
        String itemName = inventoryPage.getFirstItemTitle();
        Assert.assertFalse(itemName.isEmpty(), "No items are displayed!");
    }

    @Test(priority = 5)
    public void testAddMultipleItemsToCart() {
        inventoryPage.addFirstItemToCart();
        inventoryPage.addFirstItemToCart();
        WebElement cartBadge = inventoryPage.getCartBadge();
        Waits.waitForElementVisible(driver, cartBadge);
        Assert.assertEquals(cartBadge.getText(), "2", "Quantity is incorrect!");
    }

    @Test(priority = 6)
    public void testPriceFilterLowToHigh() {
        inventoryPage.selectFilter("Price (low to high)");
        List<Double> prices = inventoryPage.getItemPrices().stream()
                .map(element -> Double.parseDouble(ElementActions.getText(driver, element).replace("$", "")))
                .collect(Collectors.toList());
        List<Double> sortedPrices = new java.util.ArrayList<>(prices);
        sortedPrices.sort(Double::compareTo);
        Assert.assertEquals(prices, sortedPrices, "Items are not sorted by price!");
    }

//    @Test(priority = 7)
//    public void testLogoutFunctionality() {
//        // Arrange
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login("standard_user", "secret_sauce");
//
//        // Act
//        InventoryPage inventoryPage = new InventoryPage(driver);
//        inventoryPage.logout();
//
//        // Assert with synchronization
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));
//
//        // Verify login page elements are present [[2]]
//        Assert.assertTrue(loginPage.isLoginFormDisplayed(),
//                "Logout failed - Login form not visible");
//    }

    @Test(priority = 8)
    public void testItemPriceDisplay() {
        String price = inventoryPage.getFirstItemPrice();
        Assert.assertTrue(price.startsWith("$"), "Price format is incorrect!");
        Assert.assertFalse(price.equals("$"), "Price value is missing!");
    }

    @Test(priority = 9)
    public void testRemoveItemFromCart() {
        inventoryPage.addFirstItemToCart();
        inventoryPage.removeFirstItemFromCart();
        Assert.assertTrue(driver.findElements(By.className("shopping_cart_badge")).isEmpty(),
                "Item was not removed from cart!");
    }

    @Test(priority = 10)
    public void testDefaultItemsCount() {
        int count = inventoryPage.getItemsCount();
        Assert.assertEquals(count, 6, "Incorrect number of items displayed!");
    }
}
