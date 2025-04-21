package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import SwagLabs.pages.InventoryPage;
import SwagLabs.pages.LoginPage;
import SwagLabs.utilities.BaseTest;
import SwagLabs.utilities.ConfigReader;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryTests extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setupTest() {
        // Automatic login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(ConfigReader.getProperty("valid.username"));
        loginPage.enterPassword(ConfigReader.getProperty("valid.password"));
        loginPage.clickLogin();

        // Initialize inventory page
        inventoryPage = new InventoryPage(driver);
    }

    @Test(priority = 1)
    public void testInventoryPageTitle() {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Swag Labs", "Page title does not match!");
    }

    @Test(priority = 2)
    public void testAddItemToCart() {
        // 3. Add first item to cart
        inventoryPage.addFirstItemToCart();

        // 4. Verify cart badge appears
        Assert.assertTrue(driver.findElement(By.className("shopping_cart_badge")).isDisplayed(), "Item was not added to cart!");
    }

    @Test(priority = 3)
    public void testOpenShoppingCart() {
        inventoryPage.addFirstItemToCart();
        inventoryPage.openShoppingCart();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/cart.html"));

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
        inventoryPage.addFirstItemToCart(); // Add same item twice (quantity test)

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("shopping_cart_badge"), "2"));

        Assert.assertEquals(
                driver.findElement(By.className("shopping_cart_badge")).getText(),
                "2",
                "Quantity is incorrect!"
        );
    }

    @Test(priority = 6)
    public void testPriceFilterLowToHigh() {
        // تطبيق الفلتر والتحقق من الأسعار
        inventoryPage.selectFilter("Price (low to high)");

        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        List<Double> priceValues = prices.stream()
                .map(p -> Double.parseDouble(p.getText().replace("$", "")))
                .collect(Collectors.toList());

        List<Double> sortedPrices = new ArrayList<>(priceValues);
        Collections.sort(sortedPrices);

        Assert.assertEquals(priceValues, sortedPrices, "Items are not sorted by price correctly!");
    }

//
//    public void testLogoutFunctionality() {
//        inventoryPage.logout();
//
//        // Wait for the URL to change to the login page
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));
//
//        Assert.assertEquals(
//                driver.getCurrentUrl(),
//                "https://www.saucedemo.com/", // تحقق من الرابط بالضبط
//                "Logout failed or not redirected to login page!"
//        );
//    }

    @Test(priority = 8)
    public void testItemPriceDisplay() {
        String price = inventoryPage.getFirstItemPrice();
        Assert.assertTrue(price.startsWith("$"), "Price format is incorrect!");
        Assert.assertFalse(price.replace("$", "").isEmpty(), "Price value is missing!");
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
