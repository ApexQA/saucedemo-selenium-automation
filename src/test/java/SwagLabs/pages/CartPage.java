package SwagLabs.pages;

import SwagLabs.utilities.ElementActions;
import SwagLabs.utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    //Variables
    private WebDriver driver;
    //Constructor
    public CartPage(WebDriver driver){
        this.driver=driver;
    }
    //Locators
    private final By productName= By.cssSelector(".inventory_item_name");
    private final By productPrice= By.cssSelector(".inventory_item_price");
    private final By checkoutButton = By.cssSelector(".checkout_button");

    //Actions
    public String getProductName(){
        return ElementActions.getText(driver,productName);
    }
    public String getProductPrice(){
        return ElementActions.getText(driver,productPrice);
    }
    public CartPage clickCheckoutButton(){
        ElementActions.clickElement(driver, checkoutButton);
        return this;
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

