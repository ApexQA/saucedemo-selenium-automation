package SwagLabs.pages;

import SwagLabs.utilities.ElementActions;
import SwagLabs.utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    //Variables
    private WebDriver driver;
    //Constructor
    public CartPage(WebDriver driver){
        this.driver=driver;
    }
    //Locators
    private final By productNameCart= By.cssSelector(".inventory_item_name");
    private final By productPriceCart= By.cssSelector(".inventory_item_price");
    private final By checkoutButton=By.linkText("CHECKOUT");
    private final By continueShoppingButton=By.cssSelector("a[class='btn_secondary']");


    //Actions

    public String getProductName(){
        return ElementActions.getText(driver,productNameCart);
    }

    public String getProductPrice(){
        return ElementActions.getText(driver,productPriceCart);
    }

    public CartPage clickCheckoutButton(){
        ElementActions.clickElement(driver,checkoutButton);
        return this;
    }

    public CartPage removeProductByName(String productName) {
        By removeButton = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='cart_item']//button[text()='Remove']");
        ElementActions.clickElement(driver, removeButton);
        return this;
    }

    public boolean isProductInCart(String productName) {
        List<WebElement> products = driver.findElements(productNameCart);
        for (WebElement product : products) {
            if (product.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }
    public int getProductCountInCart() {
        return driver.findElements(productNameCart).size();
    }

    public InventoryPage clickContinueShoppingButton() {

        ElementActions.clickElement(driver, continueShoppingButton);
        return new InventoryPage(driver);
    }

    //Validations
    public CartPage assertProductDetails(String productName, String productPrice){
        String actualProductName= getProductName();
        String actualProductPrice= getProductPrice();
        CustomSoftAssertions.softAssertion.assertEquals(actualProductName,productName,"Product Name mismatch");
        CustomSoftAssertions.softAssertion.assertEquals(actualProductPrice,productPrice,"Product Price mismatch");
        return this;
    }

}

