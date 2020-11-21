package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePajeObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    public void testChangeScreenOrientationSearchResult(){

        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePajeObject ArticlePajeObject = new ArticlePajeObject(driver);
        String title_before_rotation = ArticlePajeObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePajeObject.getArticleTitle();

        assertEquals(
                "Article title was changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        String title_after_second_rotation = ArticlePajeObject.getArticleTitle();

        assertEquals(
                "Article title was changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }



    @Test
    public void testCheckSearchArticleInBackground(){

        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(3);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
