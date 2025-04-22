package swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import swaglabs.utilities.ElementActions;

public class CheckoutPage {

    //variables
    private WebDriver driver;

    private final By checkoutButton = By.id("checkout");

    //constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    //Actions
    // @Step("Click on checkout button")
    public void clickCheckoutButton() {
        ElementActions.clickElementBy(driver, checkoutButton);
    }
}
