package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePajeObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle()
    {
        SearchPageObject SearchPageObject= SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePajeObject ArticlePajeObject = ArticlePageObjectFactory.get(driver);
        String article_title=ArticlePajeObject.getArticleTitle();

        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    //Тема 3 урок 1
    @Test
    public  void testSwipeArticle(){

        SearchPageObject SearchPageObject= SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePajeObject ArticlePajeObject = ArticlePageObjectFactory.get(driver);
        ArticlePajeObject.waitForTitleElement();
        ArticlePajeObject.swipeToFooter();
    }



    //Домашнее задание №3 ex 6
    @Test
    public void testAssertElementPresent(){
        SearchPageObject SearchPageObject= SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePajeObject ArticlePajeObject = ArticlePageObjectFactory.get(driver);
        ArticlePajeObject.assertTitlePresentInArticle();


    }
}
