package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.locators.RelativeLocator;
import utilities.*;

import java.time.Duration;
import java.util.List;

public class HomePage {
    //Code

    //Variables
    private WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    //locators
    private final By  cartIcon = By.cssSelector("[data-test='shopping-cart-link']");


    //actions
    @Step("Navigate to home page")
    public HomePage navigateToHomePage(){
        BrowserActions.navigateToURL(driver, PropertiesUtils.getPropertyValue("homeURL"));
        return  this;
    }

    @Step("Add specific product to cast")
    public HomePage addSpecificProductToCart(String productName){
        LogsUtil.info("Adding" + productName + "to cart");
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='" + productName + "']"));
        ElementActions.clickElement(driver, addToCartButton);
        return this;
    }

    @Step("Click cart icon")
    public CartPage clickCartIcon(){
        ElementActions.clickElement(driver,cartIcon);
        return new CartPage(driver);
    }

    //Validations
    @Step("Assert product added to cart")
    public HomePage assertProductAddedToCart(String productName){
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='" + productName + "']"));
        String actualValue =  ElementActions.getText(driver, addToCartButton);
        LogsUtil.info("Actual value is: " + actualValue);
        String errMSG = "Product not added to cart";
        Validations.validateEquals(actualValue, "Remove", errMSG);
        LogsUtil.info(productName + " added to cart successfully");
        return this;
    }

}
