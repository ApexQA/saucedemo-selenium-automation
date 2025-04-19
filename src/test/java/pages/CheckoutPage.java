package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementActions;

public class CheckoutPage {

    //variables
    private WebDriver driver;

    //locators
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
