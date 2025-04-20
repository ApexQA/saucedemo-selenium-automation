package SwagLabs.Drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Map;

public class BrowserFactory {
    public static WebDriver getBrowser(String browserName){
        switch (browserName.toLowerCase()){

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                firefoxOptions.addArguments("--disable-extensions");
                firefoxOptions.addArguments("--disable-infobars");
                firefoxOptions.addArguments("--disable-notifications");
                firefoxOptions.addArguments("--remote-allow-origins=*");
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                firefoxOptions.setAcceptInsecureCerts(true);
                return new FirefoxDriver(firefoxOptions);

            case "safari":
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                safariOptions.setAutomaticInspection(false);
                safariOptions.setAutomaticProfiling(false);
                return new SafariDriver(safariOptions);

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--disable-extensions");
                edgeOptions.addArguments("--disable-notifications");
                edgeOptions.addArguments("--remote-allow-origins=*");
                Map<String, Object> edgePrefs = Map.of("profile.default_content_setting_values.notifications", false,
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "autofill.profile.enabled", false);
                edgeOptions.setExperimentalOption("prefs", edgePrefs);
                edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                //chromeOptions.addArguments("--headless");
                return new EdgeDriver(edgeOptions);

            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--remote-allow-origins=*");
                Map<String, Object> chromePrefs = Map.of("profile.default_content_setting_values.notifications", false,
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "autofill.profile.enabled", false);
                chromeOptions.setExperimentalOption("prefs", chromePrefs);
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                //chromeOptions.addArguments("--headless");
                return new ChromeDriver(chromeOptions);
        }
    }
}

