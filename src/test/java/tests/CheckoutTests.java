// CheckoutTests.java
package tests;

import org.testng.annotations.Test;
import swaglabs.pages.*;
import swaglabs.utilities.BaseTest;

import static org.testng.Assert.*;

public class CheckoutTests extends BaseTest {

//    @Test
//    public void testSuccessfulCheckoutFlow() {
//        String firstName = "John";
//        String lastName = "Doe";
//        String zip = "12345";
//
//        CheckoutPage checkoutPage = new InventoryPage(driver)
//                .addMultipleItemsToCart(2)
//                .openShoppingCart()
//                .proceedToCheckout();
//
//        checkoutPage.enterFirstName(firstName)
//                .enterLastName(lastName)
//                .enterZipCode(zip)
//                .clickContinue();
//
//        // Add assertions for checkout summary
//        assertTrue(checkoutPage.getTotalAmount().contains("$"), "Total amount should contain currency symbol");
//    }
//
//    @Test
//    public void testMissingInformationValidation() {
//        CheckoutPage checkoutPage = new InventoryPage(driver)
//                .addMultipleItemsToCart(1)
//                .openShoppingCart()
//                .proceedToCheckout();
//
//        checkoutPage.clickContinue();
//
//        String expectedError = "Error: First Name is required";
//        assertEquals(checkoutPage.getErrorMessage(), expectedError, "Error message mismatch");
//    }
//
//    @Test
//    public void testCancelCheckout() {
//        CartPage cartPage = new InventoryPage(driver)
//                .addMultipleItemsToCart(1)
//                .openShoppingCart()
//                .proceedToCheckout()
//                .clickCancel();
//
//        assertTrue(cartPage.isCartDisplayed(), "Should return to cart page");
//    }
}