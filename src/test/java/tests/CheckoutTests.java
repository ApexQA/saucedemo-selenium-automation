package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import swaglabs.pages.CartPage;
import swaglabs.pages.CheckoutPage;
import swaglabs.pages.InventoryPage;
import swaglabs.pages.LoginPage;
import swaglabs.utilities.BaseTest;
import swaglabs.utilities.ConfigReader;
import swaglabs.utilities.CustomSoftAssertions;

import java.util.Arrays;

public class CheckoutTests extends BaseTest {
    private InventoryPage inventoryPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setup() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password"));
        inventoryPage = new InventoryPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

//    @Test(description = "Valid checkout flow [[6]]")
//    public void testValidCheckout() {
//        inventoryPage.addFirstItemToCart()
//                .openShoppingCart()
//                .clickCheckoutButton();
//
//        checkoutPage.enterShippingInfo("John", "Doe", "12345")
//                .proceedToOverview()
//                .validateTotalAmount("Total: $29.99") // Validate pricing [[7]]
//                .completeCheckout();
//
//        CustomSoftAssertions.softAssertion.assertTrue(
//                checkoutPage.isConfirmationDisplayed(),
//                "Order confirmation not shown [[6]]"
//        );
//        CustomSoftAssertions.customAssertAll();
//    }
//
//    @Test(description = "Invalid postal code validation [[4]]")
//    public void testInvalidPostalCode() {
//        inventoryPage.addFirstItemToCart()
//                .openShoppingCart()
//                .clickCheckoutButton();
//
//        checkoutPage.enterShippingInfo("John", "Doe", "123")
//                .validateErrorMessage("Error: Postal Code is required")
//                .proceedToOverview(); // Should not proceed [[4]]
//
//        CustomSoftAssertions.softAssertion.assertFalse(
//                checkoutPage.isConfirmationDisplayed(),
//                "Checkout should fail with invalid ZIP [[4]]"
//        );
//        CustomSoftAssertions.customAssertAll();
//    }
//
//    @Test(description = "Empty form submission validation [[4]]")
//    public void testEmptyFormSubmission() {
//        inventoryPage.addFirstItemToCart()
//                .openShoppingCart()
//                .clickCheckoutButton();
//
//        checkoutPage.enterShippingInfo("", "", "")
//                .validateErrorMessage("Error: First Name is required")
//                .proceedToOverview(); // Should not proceed [[4]]
//
//        CustomSoftAssertions.softAssertion.assertFalse(
//                checkoutPage.isConfirmationDisplayed(),
//                "Checkout should fail with empty form [[4]]"
//        );
//        CustomSoftAssertions.customAssertAll();
//    }
//
//    @Test(description = "Full cart validation flow [[3]][[7]]")
//    public void validateCheckoutFlow() {
//        CartPage cart = inventoryPage.addMultipleItemsToCart(2)
//                .openShoppingCart();
//
//        cart.validateAllProducts(
//                Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light"),
//                Arrays.asList("$29.99", "$9.99")
//        );
//
//        checkoutPage = cart.clickCheckout();
//        checkoutPage.enterShippingInfo("Test", "User", "94105")
//                .proceedToOverview()
//                .validateTotalAmount("Total: $39.98") // Sum of both items [[7]]
//                .completeCheckout();
//
//        CustomSoftAssertions.softAssertion.assertTrue(
//                checkoutPage.isConfirmationDisplayed(),
//                "Confirmation message not shown [[6]]"
//        );
//        CustomSoftAssertions.customAssertAll();
//    }
}