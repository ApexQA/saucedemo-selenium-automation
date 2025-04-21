package swaglabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import swaglabs.utilities.ElementActions;

public class CheckoutPage {

    //variables
    private WebDriver driver;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    //constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    //Actions
    // @Step("Click on checkout button")
    public void clickCheckoutButton() {
        ElementActions.clickElement(driver, checkoutButton);
    }
}
