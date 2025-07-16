package by.onliner.tests;

import by.onliner.model.pages.MainPage;
import by.onliner.model.pages.ProductPricingPage;
import org.junit.jupiter.api.Test;

public class CartTest extends BaseTest{

    @Test
    public void testAddingProductIntoCartFromProductsPage() throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.typeTextInSearchField("Смартфон Samsung Galaxy 4GB/128GB (черный)");
        ProductPricingPage productPricingPage = mainPage.selectProductFromListByClickingOnOrangeButton(0);
        productPricingPage.selectByPriceAscendingOption();
        Thread.sleep(5000);
    }
}
