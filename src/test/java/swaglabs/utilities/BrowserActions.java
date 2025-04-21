package swaglabs.utilities;

import org.openqa.selenium.WebDriver;

public class BrowserActions {

    //Constructor
    private BrowserActions() {
    }

    //Navigate to URL
    public static void navigateToURL(WebDriver driver, String url) {
        driver.get(url);
    }

    //Get current URL
    public static String getCurrentURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    //Get page title
    public static String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    //refresh page
    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    //close Browser
    public static void closeBrowser(WebDriver driver) {
        driver.quit();
    }
}

