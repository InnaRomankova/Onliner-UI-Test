package by.onliner.core.driver;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static by.onliner.core.properties.ProjectProperties.getProperty;
import static by.onliner.testData.UrlData.HOST_URL;

@Log4j2
public class WebDriverFactory {

    public static WebDriver getWebDriver() {
        RemoteWebDriver driver = null;
        String browser = getProperty("browser").toLowerCase();

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = getChromeOptions();
                driver = createDriver(chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = getFirefoxOptions();
                driver = createDriver(firefoxOptions);
                break;
            default:
                throw new RuntimeException("No supported browser: " + browser);
        }
        if (driver != null) {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().window().maximize();
        }

        log.info("Created WebDriver instance: " + driver);
        return driver;
    }

    private static RemoteWebDriver createDriver(MutableCapabilities options) {
        try {
            return new RemoteWebDriver(new URL(HOST_URL), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL", e);
        }
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--ignore-certificate-errors",
                "--disable-web-security",
                "--disable-popup-blocking"
        );

        Map<String, Object> chromePref = new HashMap<>();
        chromePref.put("profile.default_content_settings.popups", 0);
        options.setExperimentalOption("prefs", chromePref);

        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--ignore-certificate-errors");

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("dom.disable_open_during_load", true);
        profile.setPreference("privacy.popups.showBrowserMessage", false);

        firefoxOptions.setProfile(profile);

        return firefoxOptions;
    }
}
