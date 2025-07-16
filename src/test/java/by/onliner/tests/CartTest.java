package by.onliner.tests;

import by.onliner.model.pages.MainPage;
import by.onliner.model.pages.ProductPricingPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest {

    @Test
    public void testAddingProductIntoCartFromProductsPage() throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.typeTextInSearchField("Смартфон Samsung Galaxy 4GB/128GB (черный)");
        ProductPricingPage productPricingPage = mainPage.selectProductFromListByClickingOnOrangeButton(0);
        productPricingPage.selectByPriceAscendingOption();
        productPricingPage.clickToCartButtonForProduct(0);

        Assertions.assertAll("Verify the box with product, text 'Product added to cart' and the buttons" +
                        " 'Go to cart' and 'Continue shopping' appeared on the screen",
                () -> assertTrue(productPricingPage.verifyTextProductAddedToCartIsDisplayed()),
                () -> assertTrue(productPricingPage.verifyButtonContinueShoppingIsDisplayed()),
                () -> assertTrue(productPricingPage.verifyButtonGoToCartIsDisplayed()),
                () -> assertEquals(1, productPricingPage.getIconValueOfCountShoppingCart())
        );
    }
}
