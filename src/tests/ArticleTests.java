package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePajeObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle()
    {
        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePajeObject ArticlePajeObject = new ArticlePajeObject(driver);
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

        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePajeObject ArticlePajeObject = new ArticlePajeObject(driver);
        ArticlePajeObject.waitForTitleElement();
        ArticlePajeObject.swipeToFooter();
    }



    //Домашнее задание №3 ex 6
    @Test
    public void testAssertElementPresent(){
        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePajeObject ArticlePajeObject = new ArticlePajeObject(driver);
        ArticlePajeObject.assertTitlePresentInArticle();


    }
}
