package swaglabs.utilities;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import swaglabs.drivers.BrowserFactory;
import swaglabs.drivers.DriverManager;

import java.util.Map;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        String browser = ConfigReader.getProperty("browser");
        driver = DriverManager.createInstance(browser); // Use DriverManager [[5]]
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("app.url"));
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--incognito");
        Map<String, Object> chromePrefs = Map.of("profile.default_content_setting_values.notifications", false,
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile.enabled", false);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

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