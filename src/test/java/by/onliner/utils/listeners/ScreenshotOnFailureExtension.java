package by.onliner.utils.listeners;

import by.onliner.core.driver.WebDriverSingleton;
import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

@Log4j2
public class ScreenshotOnFailureExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.error("Test failed: {}", context.getDisplayName(), cause);
        takeScreenshot(context.getDisplayName());
        WebDriverSingleton.closeDriver();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        closeDriver();
    }

    private void takeScreenshot(String testName) {
        try {
            if (WebDriverSingleton.getDriver() != null) {
                byte[] screenshot = ((TakesScreenshot) WebDriverSingleton.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot after failure: " + testName,
                        new ByteArrayInputStream(screenshot));
            }
        } catch (Exception e) {
            log.error("Failed to take screenshot", e);
        }
    }

    private void closeDriver() {
        try {
            WebDriverSingleton.closeDriver();
        } catch (Exception e) {
            log.error("Failed to close driver", e);
        }
    }
}
