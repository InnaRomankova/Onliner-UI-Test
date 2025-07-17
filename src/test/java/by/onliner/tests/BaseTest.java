package by.onliner.tests;

import by.onliner.core.driver.WebDriverSingleton;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import static by.onliner.testData.UrlData.MAIN_PAGE_URL;

@Log4j2
public class BaseTest {

    protected WebDriver webDriver;

    @BeforeEach
    public void startSession() {
        log.info("Starting webDriver session for thread: " + Thread.currentThread());
        webDriver = WebDriverSingleton.getDriver();
        webDriver.manage().deleteAllCookies();
        webDriver.get(MAIN_PAGE_URL);
    }
}
