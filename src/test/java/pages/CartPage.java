package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    //Locators
    private WebDriver driver;
    //private final By CheckoutButton = By.("a.btn_action checkout_button");
    private final By cartIcon = By.xpath("//svg[@data-icon='shopping-cart']");

    //constructor
    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    //Actions
    public void clickCheckoutButton(){
        driver.findElement(cartIcon).click();
    }
}
