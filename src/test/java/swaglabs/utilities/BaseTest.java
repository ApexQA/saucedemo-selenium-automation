package swaglabs.utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import swaglabs.drivers.DriverManager;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        String browser = ConfigReader.getProperty("browser");
        driver = DriverManager.createInstance(browser);
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("app.url"));
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}