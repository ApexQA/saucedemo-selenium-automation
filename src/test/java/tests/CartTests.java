package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class CartTests {
    private WebDriver driver;
    @Test(dependsOnMethods = "addingProductCart")
    public void checkoutProduct(){

    }
}
