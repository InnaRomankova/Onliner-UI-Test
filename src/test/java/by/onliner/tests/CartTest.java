package by.onliner.tests;

import by.onliner.model.pages.MainPage;
import by.onliner.model.pages.ProductPricingPage;
import by.onliner.utils.listeners.ScreenshotOnFailureExtension;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ScreenshotOnFailureExtension.class)
public class CartTest extends BaseTest {

    @Test
    @Tag("sanity")
    @Story("CartTest")
    @DisplayName("Adding the product into the cart from product’s page")
    public void testAddingProductIntoCartFromProductsPage() {
        MainPage mainPage = new MainPage();
        mainPage.typeTextInSearchField("Смартфон Samsung Galaxy 4GB/128GB (черный)");
        ProductPricingPage productPricingPage = mainPage.selectProductFromListByClickingOnOrangeButton(0);
        productPricingPage.selectByPriceAscendingOption();
        productPricingPage.clickToCartButtonForProduct(0);

        Assertions.assertAll("Verify the box with product, text 'Product added to cart' and the buttons" +
                        " 'Go to cart' and 'Continue shopping' appeared on the screen",
                () -> assertTrue(productPricingPage.verifyTextProductAddedToCartIsDisplayed(), "Text 'Product added to cart' is not displayed"),
                () -> assertTrue(productPricingPage.verifyButtonContinueShoppingIsDisplayed(), "Button 'Continue shopping' is not displayed"),
                () -> assertTrue(productPricingPage.verifyButtonGoToCartIsDisplayed(), "Button 'Go to cart' is not displayed"),
                () -> assertEquals(1, productPricingPage.getIconValueOfCountShoppingCart(), "An icon value of count shopping cart is not changed on 1")
        );
    }

    @Test
    @Tag("negative")
    @Story("CartTest")
    @DisplayName("Adding the product into the cart from product’s page")
    public void testAddingProductFailingForAllureScreenshot() {
        MainPage mainPage = new MainPage();
        mainPage.typeTextInSearchField("Смартфон Samsung Galaxy 4GB/128GB (черный)");
        ProductPricingPage productPricingPage = mainPage.selectProductFromListByClickingOnOrangeButton(0);
        productPricingPage.selectByPriceAscendingOption();
        productPricingPage.clickToCartButtonForProduct(0);

        Assertions.assertAll("Verify the box with product, text 'Product added to cart' and the buttons" +
                        " 'Go to cart' and 'Continue shopping' appeared on the screen",
                () -> assertTrue(productPricingPage.verifyTextProductAddedToCartIsDisplayed(), "Text 'Product added to cart' is not displayed"),
                () -> assertTrue(productPricingPage.verifyButtonContinueShoppingIsDisplayed(), "Button 'Continue shopping' is not displayed"),
                () -> assertTrue(productPricingPage.verifyButtonGoToCartIsDisplayed(), "Button 'Go to cart' is not displayed"),
                () -> assertEquals(3, productPricingPage.getIconValueOfCountShoppingCart(), "An icon value of count shopping cart is not changed on 1")
        );
    }
}
