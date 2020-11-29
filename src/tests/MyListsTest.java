package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTest extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList(){

        SearchPageObject SearchPageObject= SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = MyListPageObjectFactory.get(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    //Домашнее задание №3 ex 5
    @Test
    public void testAddTwoArticlesAndDelOne(){

        //Add first article to my list
        SearchPageObject SearchPageObject= SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String first_article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        //Add second article

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");
        String second_article_title = ArticlePageObject.getArticleTitle();

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addSecondArticleToMyList(name_of_folder);

        //Open my list and delete on of the articles
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = MyListPageObjectFactory.get(driver);
        MyListsPageObject.openFolderByName(name_of_folder);

        MyListsPageObject.waitForElementPresentInMyList(first_article_title);
        MyListsPageObject.waitForElementPresentInMyList(second_article_title);
        MyListsPageObject.swipeByArticleToDelete(first_article_title);
        MyListsPageObject.waitForElementPresentInMyList(second_article_title);
        MyListsPageObject.waitForElementNotPresentInMyList(first_article_title);

        //checking title of saved article
        MyListsPageObject.clickOnSomeArticle(second_article_title);
        ArticlePageObject.assertArticleHasExpectedTitle("Appium");

    }
}
