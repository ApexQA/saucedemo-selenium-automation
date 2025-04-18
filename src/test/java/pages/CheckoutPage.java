package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementActions;

public class CheckoutPage {

    //Locators
    private WebDriver driver;
    //private final By CheckoutButton = By.("a.btn_action checkout_button");
    private final By checkoutButton = By.xpath("//a[@href='./checkout-step-one.html']");

    //constructor
    public CheckoutPage(WebDriver driver){
        this.driver = driver;
    }

    //Actions
    public void clickCheckoutButton(){
        ElementActions.clickElement(driver, checkoutButton);
    }


    //Validations




    //Assertions
}
