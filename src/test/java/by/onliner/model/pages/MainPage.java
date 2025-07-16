package by.onliner.model.pages;

import by.onliner.core.element.Element;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {

    Element searchField = Element.byName("query");
    Element productListFrame = Element.byClassName("modal-iframe");
    Element product = Element.byCssSelector(".result__item.result__item_product");
    Element orangeButton = Element.byCssSelector(".button_orange");

    @Step("Enter valid value \"{text}\" in the search field")
    public void typeTextInSearchField(String text) {
        searchField.type(text);
    }

    @Step("Select the {serialNumber} product from the list by clicking on product name")
    public SearchedProductPage selectProductFromListByClickingOnProductName(int serialNumber) {
        productListFrame.switchToFrame();
        product.waitForVisibility(5);
        List<WebElement> products = product.getElements();
        products.get(serialNumber).click();
        return new SearchedProductPage();
    }

    @Step("Select the {serialNumber} product from the list by clicking on orange button")
    public ProductPricingPage selectProductFromListByClickingOnOrangeButton(int serialNumber) {
        productListFrame.switchToFrame();
        orangeButton.waitForVisibility(5);
        List<WebElement> orangeButtons = orangeButton.getElements();
        orangeButtons.get(serialNumber).click();
        return new ProductPricingPage();
    }
}
