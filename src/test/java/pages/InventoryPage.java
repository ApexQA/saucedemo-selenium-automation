package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By itemTitle = By.className("inventory_item_name");
    private By addToCartButton = By.xpath("//button[contains(text(), 'Add to cart')]");
    private By shoppingCartIcon = By.className("shopping_cart_link");
    private By itemPrice = By.className("inventory_item_price");
    private By filterDropdown = By.className("product_sort_container");
    private By logoutButton = By.id("logout_sidebar_link");
    private By menuButton = By.id("react-burger-menu-btn");
    private By removeButton = By.xpath("//button[contains(text(), 'Remove')]");

    // Constructor
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    // Methods
    public String getFirstItemTitle() {
        return driver.findElement(itemTitle).getText();
    }

    public void addFirstItemToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void openShoppingCart() {
        driver.findElement(shoppingCartIcon).click();
    }

    public String getFirstItemPrice() {
        return driver.findElement(itemPrice).getText();
    }

    public void selectFilter(String optionText) {
        driver.findElement(filterDropdown).click();
        driver.findElement(By.xpath("//option[text()='" + optionText + "']")).click();
    }

    public void logout() {
        System.out.println("Opening menu...");

        // Wait for the menu button to be clickable and click it
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
        System.out.println("Menu opened successfully.");

        // Wait for the logout button to be visible and click it
        System.out.println("Waiting for logout button to be visible...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton)).click();
        System.out.println("Logout button clicked.");
    }

    public void removeFirstItemFromCart() {
        driver.findElement(removeButton).click();
    }

    public int getItemsCount() {
        return driver.findElements(itemTitle).size();
    }
}
