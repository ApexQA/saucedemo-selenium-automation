package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import swaglabs.utilities.*;

import java.util.List;

public class CartPage extends BasePage {
    //locators
    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> productName;

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> productPrices;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;


    @FindBy(css = ".empty_cart_message")
    private WebElement emptyCartMessage;

    @FindBy(css= "#continue-shopping")
    private WebElement continueShoppingButton;


    //Constructor
    public CartPage(WebDriver driver) {
        super(driver);
        Waits.waitForElementVisible(driver, checkoutButton);
    }

    public int getCartItemCount() {
        return getCartItems().size();
    }

    public List<WebElement> getCartItems() {
        return driver.findElements(By.cssSelector(".cart_item")); // Dynamic retrieval [[2]]
    }

    public String getProductName(int index) {
        return ElementActions.getText(driver, productName.get(index));
    }

    public String getProductPrice(int index) {
        return ElementActions.getText(driver, productPrices.get(index));
    }

    public CheckoutPage proceedToCheckout() {
        Waits.waitForElementClickable(driver, checkoutButton); // Wait for clickability [[7]]
        ElementActions.clickElement(driver, checkoutButton);
        return new CheckoutPage(driver);
    }

    public boolean isCartDisplayed() {
        return ElementActions.isElementDisplayed(driver, checkoutButton); // Verify cart state [[8]]
    }

    public InventoryPage clickContinueShoppingButton() {
        Waits.waitForElementClickable(driver, continueShoppingButton);
        ElementActions.clickElement(driver, continueShoppingButton);
        return new InventoryPage(driver);
    }

    public void removeAllItems() {
        int itemCount;
        do {
            itemCount = getCartItemCount();
            if (itemCount > 0) {
                // Use dynamic lookup to avoid stale elements [[7]]
                WebElement firstItem = driver.findElement(By.cssSelector(".cart_item"));
                WebElement removeBtn = firstItem.findElement(
                        By.cssSelector("[data-test^='remove-']")); // Matches any remove button [[1]]

                ElementActions.clickElement(driver, removeBtn);

                Waits.waitForCartItemCountToBeLessThan(driver, itemCount);
            }
        } while (itemCount > 0);
    }

    public void removeItem(String productName) {
        String formattedName = productName.toLowerCase()
                .replace(" ", "-") // Convert to kebab-case [[2]]
                .replaceAll("[^a-z0-9-]", ""); // Sanitize special characters [[3]]
        String selector = String.format("[data-test='remove-%s']", formattedName);

        WebElement removeBtn = Waits.waitForElementPresent(driver, By.cssSelector(selector));
        ElementActions.clickElement(driver, removeBtn);
    }

    // Validations
    public CartPage validateAllProducts(List<String> names, List<String> prices) {
        for (int i = 0; i < getCartItemCount(); i++) {
            CustomSoftAssertions.softAssertion.assertEquals(
                    getProductName(i),
                    names.get(i),
                    "Product name mismatch at index " + i // [[4]]
            );
            CustomSoftAssertions.softAssertion.assertEquals(
                    getProductPrice(i),
                    prices.get(i),
                    "Price mismatch at index " + i
            );
        }
        return this;
    }

    public CartPage validateEmptyCart() {
        List<WebElement> items = driver.findElements(By.cssSelector(".cart_item"));
        CustomSoftAssertions.softAssertion.assertEquals(
                items.size(), 0, "Cart is not empty as expected");
        return this;
    }

    public CartPage clickCheckoutButton(){
        ElementActions.clickElement(driver, checkoutButton);
        return this;
    }
}