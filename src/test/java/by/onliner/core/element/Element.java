package by.onliner.core.element;

import by.onliner.core.driver.WebDriverSingleton;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Log4j2
public class Element {

    private final By by;
    private final WebDriver webDriver;
    private final WebDriverWait wait;

    public Element(By by) {
        this.by = by;
        this.webDriver = WebDriverSingleton.getDriver();
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public static Element byXpath(String xpath, Object... params) {
        String locator = String.format(xpath, params);
        return new Element(By.xpath(locator));
    }

    public static Element byName(String name) {
        return new Element(By.name(name));
    }

    public static Element byClassName(String className) {
        return new Element(By.className(className));
    }

    public static Element byCssSelector(String cssSelector) {
        return new Element(By.cssSelector(cssSelector));
    }

    public static Element byTagName(String tagName) {
        return new Element(By.tagName(tagName));
    }

    public WebElement getElement() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> getElements() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void click() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }

    public void type(String text) {
        WebElement element = getElement();
        element.clear();
        element.sendKeys(text);
    }

    public String getText() {
        return getElement().getText();
    }

    public boolean isDisplayed(int timeOutInSeconds) {
        boolean isDisplayed;
        try {
            isDisplayed = waitForVisibility(timeOutInSeconds) != null;
        } catch (TimeoutException e) {
            isDisplayed = false;
        }
        return isDisplayed;
    }

    public WebElement waitForVisibility(int timeOutInSeconds) {
        log.info("Wait element will be visible");
        return new WebDriverWait(webDriver, Duration.ofSeconds(timeOutInSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void switchToFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
    }

    public void moveToElement() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(getElement()).perform();
    }

    public void selectOptionByValue(String optionValue) {
        Select select = new Select(getElement());
        select.selectByValue(optionValue);
    }
}
