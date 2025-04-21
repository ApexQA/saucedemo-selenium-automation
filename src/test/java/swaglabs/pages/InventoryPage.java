package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import swaglabs.utilities.ElementActions;
import swaglabs.utilities.Waits;

import java.util.List;

public class InventoryPage extends BasePage {
    // Locators with @FindBy
    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemTitles;

    @FindBy(xpath = "//button[contains(text(), 'Add to cart')]")
    private WebElement addToCartButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(className = "product_sort_container")
    private WebElement filterDropdown;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(xpath = "//button[contains(text(), 'Remove')]")
    private WebElement removeButton;

    @FindBy(xpath = "//button[contains(text(), 'Add to cart')]")
    private List<WebElement> addButtons;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    // Actions with utility methods
    public String getFirstItemTitle() {
        return ElementActions.getText(driver, itemTitles.get(0));
    }

    public void addFirstItemToCart() {
        ElementActions.clickElement(driver, addToCartButton);
    }

    public void openShoppingCart() {
        ElementActions.clickElement(driver, shoppingCartIcon);
    }

    public InventoryPage addMultipleItemsToCart(int quantity) {
        int availableItems = addButtons.size();
        if (quantity > availableItems) {
            quantity = availableItems; // Prevent index overflow [[1]]
        }
        for (int i = 0; i < quantity; i++) {
            ElementActions.clickElement(driver, addButtons.get(i));
            Waits.waitForElementTextToBeNotEmpty(driver, cartBadge); // [[1]][[2]]
        }
        return this;
    }

    public String getFirstItemPrice() {
        return ElementActions.getText(driver, itemPrices.get(0));
    }

    public void selectFilter(String optionText) {
        ElementActions.clickElement(driver, filterDropdown);
        WebElement option = driver.findElement(By.xpath("//option[text()='" + optionText + "']"));
        ElementActions.clickElement(driver, option);
    }

//    public void logout() {
//        Waits.waitForElementClickable(driver, menuButton);
//        menuButton.click();
//        Waits.waitForElementClickable(driver, logoutButton);
//        logoutButton.click();
//
//        Waits.waitForUrlToBe(driver, "https://www.saucedemo.com/");
//    }

    public void removeFirstItemFromCart() {
        ElementActions.clickElement(driver, removeButton);
    }

    public int getItemsCount() {
        return itemTitles.size();
    }

    public WebElement getCartBadge() {
        return cartBadge;
    }

    public List<WebElement> getItemPrices() {
        return itemPrices;
    }
}
