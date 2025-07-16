package by.onliner.model.pages;

import by.onliner.core.element.Element;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {

    Element searchField = Element.byName("query");
    Element productListFrame = Element.byClassName("modal-iframe");
    Element product = Element.byCssSelector(".result__item.result__item_product");

    @Step("Enter valid value \"{text}\" in the search field")
    public void typeTextInSearchField(String text) {
        searchField.type(text);
    }

    @Step("Select the {serialNumber} product from the list")
    public SearchedProductPage selectProductFromList(int serialNumber) {
        productListFrame.switchToFrame();
        product.waitForVisibility(5);
        List<WebElement> products = product.getElements();
        products.get(serialNumber).click();
        return new SearchedProductPage();
    }
}
