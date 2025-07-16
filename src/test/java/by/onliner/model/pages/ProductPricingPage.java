package by.onliner.model.pages;

import by.onliner.core.element.Element;
import io.qameta.allure.Step;

public class ProductPricingPage {

    Element element = Element.byCssSelector("select.input-style__real");

    @Step("Select the option 'По возрастанию цены' in the field 'Сортировать по умолчанию'")
    public void selectByPriceAscendingOption() {
        element.isDisplayed(5);
        element.moveToElement();
        element.selectOptionByValue("price:asc");
    }
}
