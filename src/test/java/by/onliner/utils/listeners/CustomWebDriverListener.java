package by.onliner.utils.listeners;

import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

@Log4j2
public class CustomWebDriverListener implements WebDriverListener {

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        log.debug("Finding element: {}", locator);
        Allure.step(String.format("Finding element: " + locator.toString()));
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        log.info("Element found: {}", locator);
        Allure.step(String.format("Element found: " + locator.toString()));
    }
}
