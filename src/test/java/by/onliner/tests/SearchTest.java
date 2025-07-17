package by.onliner.tests;

import by.onliner.model.pages.MainPage;
import by.onliner.model.pages.SearchedProductPage;
import by.onliner.utils.listeners.ScreenshotOnFailureExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ScreenshotOnFailureExtension.class)
public class SearchTest extends BaseTest {

    @Test
    @Tag("sanity")
    public void testSearchProduct() {
        MainPage mainPage = new MainPage();
        mainPage.typeTextInSearchField("Смартфон Samsung Galaxy 4GB/128GB (черный)");
        SearchedProductPage searchedProductPage = mainPage.selectProductFromListByClickingOnProductName(0);

        Assertions.assertTrue(searchedProductPage.isCatalogMastheadContainsText
                ("(?=.*Samsung)(?=.*Galaxy)(?=.*4GB/128GB)(?=.*\\(черный\\))(?=.*(Телефон|Смартфон)).*"));
    }
}
