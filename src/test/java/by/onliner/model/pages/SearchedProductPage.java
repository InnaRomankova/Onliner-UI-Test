package by.onliner.model.pages;

import by.onliner.core.element.Element;
import io.qameta.allure.Step;

public class SearchedProductPage {

    Element catalogMasthead = Element.byTagName("h1");

    @Step("Is catalog masthead contains text {productNameRegExp}")
    public boolean isCatalogMastheadContainsText(String productNameRegExp) {
        return catalogMasthead.getText().matches(productNameRegExp);
    }
}
