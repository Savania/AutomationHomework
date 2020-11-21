package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePajeObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTest extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList(){

        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePajeObject ArticlePajeObject = new ArticlePajeObject(driver);
        ArticlePajeObject.waitForTitleElement();
        String article_title =ArticlePajeObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        ArticlePajeObject.addArticleToMyList(name_of_folder);
        ArticlePajeObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    //Домашнее задание №3 ex 5
    @Test
    public void testAddTwoArticlesAndDelOne(){

        //Add first article to my list
        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePajeObject ArticlePajeObject = new ArticlePajeObject(driver);
        ArticlePajeObject.waitForTitleElement();
        String first_article_title =ArticlePajeObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        ArticlePajeObject.addArticleToMyList(name_of_folder);
        ArticlePajeObject.closeArticle();

        //Add second article

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");
        String second_article_title =ArticlePajeObject.getArticleTitle();

        ArticlePajeObject.waitForTitleElement();
        ArticlePajeObject.addSecondArticleToMyList(name_of_folder);

        //Open my list and delete on of the articles
        ArticlePajeObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);

        MyListsPageObject.waitForElementPresentInMyList(first_article_title);
        MyListsPageObject.waitForElementPresentInMyList(second_article_title);
        MyListsPageObject.swipeByArticleToDelete(first_article_title);
        MyListsPageObject.waitForElementPresentInMyList(second_article_title);
        MyListsPageObject.waitForElementNotPresentInMyList(first_article_title);

        //checking title of saved article
        MyListsPageObject.clickOnSomeArticle(second_article_title);
        ArticlePajeObject.assertArticleHasExpectedTitle("Appium");

    }
}
