package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementActions;
import utilities.Validations;

public class CartPage {
    private WebDriver driver;
    //elements locators
    private By productName= By.cssSelector("inventory_item_name");
    private By productPrice= By.cssSelector("inventory_item_price");
    private By checkoutButton= By.cssSelector(".checkout_button");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    //Actions
    public String getProductName(){
        return ElementActions.getText(driver,productName);
    }
    public String getProductPrice(){
        return ElementActions.getText(driver,productPrice);
    }
    public CartPage clickCheckoutButton(){
        ElementActions.clickElement(driver,checkoutButton);
         return this;
    }

    //Validations
    public CartPage assertProductDetails(String productName, String productPrice) {
        String actualProductName = getProductName();
        String actualProductPrice = getProductPrice();
        Validations.validateEquals(actualProductName, productName, "Product name mismatch");
        Validations.validateEquals(actualProductPrice, productPrice, "Product price mismatch");
        return this;
    }
}

