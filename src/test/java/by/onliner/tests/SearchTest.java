package by.onliner.tests;

import by.onliner.model.pages.MainPage;
import by.onliner.model.pages.SearchedProductPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchTest extends BaseTest {

    @Test
    public void testSearchProduct() {
        MainPage mainPage = new MainPage();
        mainPage.typeTextInSearchField("Смартфон Samsung Galaxy 4GB/128GB (черный)");
        SearchedProductPage searchedProductPage = mainPage.selectProductFromListByClickingOnProductName(0);

        Assertions.assertTrue(searchedProductPage.isCatalogMastheadContainsText
                ("(?=.*Samsung)(?=.*Galaxy)(?=.*4GB/128GB)(?=.*\\(черный\\))(?=.*(Телефон|Смартфон)).*"));
    }
}
