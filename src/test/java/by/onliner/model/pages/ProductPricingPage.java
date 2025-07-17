package by.onliner.model.pages;

import by.onliner.core.element.Element;
import io.qameta.allure.Step;

public class ProductPricingPage {

    Element sortingElement = Element.byCssSelector(".input-style__real");
    Element toCartButton = Element.byCssSelector("div.helpers_hide_tablet a.offers-list__button_cart");
    Element textProductAddedToCart = Element.byXpath("//div[@class='product-recommended__subheader'][contains(text(),'Товар добавлен в корзину')]");
    Element buttonContinueShopping = Element.byCssSelector("a.helpers_hide_tablet");
    Element buttonGoToCart = Element.byCssSelector(".button-style_another.product-recommended__button");
    Element pageBody = Element.byTagName("body");
    Element cartQuantityCounter = Element.byCssSelector(".auth-bar__counter");

    @Step("Select the option 'По возрастанию цены' in the dropdown 'Сортировать по умолчанию'")
    public void selectByPriceAscendingOption() {
        sortingElement.selectSecondOptionInDropdown();
    }

    @Step("Click 'To cart' button for {serialNumber} product in the list")
    public void clickToCartButtonForProduct(int serialNumber) {
        toCartButton.getElements().get(serialNumber).click();
    }

    @Step("Verify text 'Товар добавлен в корзину' appeared on the screen")
    public boolean verifyTextProductAddedToCartIsDisplayed() {
        return textProductAddedToCart.isDisplayed(5);
    }

    @Step("Verify button 'Продолжить покупки' appeared on the screen")
    public boolean verifyButtonContinueShoppingIsDisplayed() {
        return buttonContinueShopping.getText().contains("Продолжить покупки");
    }

    @Step("Verify button 'Перейти в корзину' appeared on the screen")
    public boolean verifyButtonGoToCartIsDisplayed() {
        return buttonGoToCart.getText().contains("Перейти в корзину");
    }

    @Step("Get an icon value of count shopping cart")
    public int getIconValueOfCountShoppingCart() {
        pageBody.refreshPage();
        return Integer.parseInt(cartQuantityCounter.getText());
    }
}
