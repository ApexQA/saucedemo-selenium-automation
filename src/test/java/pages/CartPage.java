package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.CustomSoftAssertion;
import utilities.ElementActions;

public class CartPage {
    //variables
    private WebDriver driver;

    //private final By CheckoutButton = By.("a.btn_action checkout_button");
    private final By cartIcon = By.xpath("//svg[@data-icon='shopping-cart']");

    //constructor
    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    //Locators
    private final By productName = By.cssSelector(".inventory_item_name");
    private final By productPrice = By.cssSelector(".inventory_item_price");
    private final By checkoutButton = By.cssSelector(".checkout_button");

    //Actions
    @Step("Get product name")
    private String getProductName(){
        return ElementActions.getText(driver, productName);
    }

    @Step("Get product price")
    private String getProductPrice(){
        return ElementActions.getText(driver, productPrice);
    }

    @Step("Click checkout button")
    public InformationPage clickCheckoutButton(){
        ElementActions.clickElement(driver, checkoutButton);
        return new InformationPage(driver);
    }

    //validations
    @Step("Assert product details")
    public CartPage assertProductDetails(String productName, String ProductPrice){
        String actualProductName = getProductName();
        String actualProductPrice = getProductPrice();
        CustomSoftAssertion.softAssertion.assertEquals(actualProductName, productName, "Product name mismatch");
        CustomSoftAssertion.softAssertion.assertEquals(actualProductPrice, ProductPrice, "Product price mismatch");
        return this;
    }
}
