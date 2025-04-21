package SwagLabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import SwagLabs.utilities.ElementActions;

public class CheckoutPage {

    //variables
    private WebDriver driver;

    //locators
    private final By checkoutButton = By.id("checkout");

    //constructor
    public CheckoutPage(WebDriver driver){
        this.driver = driver;
    }

    //Actions
    public void clickCheckoutButton(){
        ElementActions.clickElement(driver, checkoutButton);
    }

}
