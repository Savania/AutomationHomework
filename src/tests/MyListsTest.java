package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTest extends CoreTestCase
{
    private static final String
            login="Sovannanas",
            password="Chris001",
            name_of_folder="Learning programming";

    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {

        SearchPageObject SearchPageObject= SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        }
        else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isMw()){
            AuthorizationPageObject AuthorizationPageObject=new AuthorizationPageObject(driver);

            AuthorizationPageObject.clickAuthButton();

            AuthorizationPageObject.enterLoginData(login,password);
            AuthorizationPageObject.submitForm();

            ArticlePageObject.waitForTitleElement();
//            assertEquals(
//                    "Wea are not on the same page after login",
//                    article_title,
//                    ArticlePageObject.getArticleTitle()
//            );
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();

        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    //Домашнее задание №3 ex 5
    @Test
    public void testAddTwoArticlesAndDelOne() throws InterruptedException {

        //Add first article to my list
        SearchPageObject SearchPageObject= SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");



        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String first_article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
        }
        else{
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticle();
        }
        if (Platform.getInstance().isMw()){
            AuthorizationPageObject AuthorizationPageObject=new AuthorizationPageObject(driver);

            AuthorizationPageObject.clickAuthButton();
            Thread.sleep(1000);
            AuthorizationPageObject.enterLoginData(login,password);
            AuthorizationPageObject.submitForm();

            ArticlePageObject.waitForTitleElement();
//            assertEquals(
//                    "Wea are not on the same page after login",
//                    article_title,
//                    ArticlePageObject.getArticleTitle()
//            );
            ArticlePageObject.addArticlesToMySaved();
        }

        //Add second article

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("c++");
        SearchPageObject.clickByArticleWithSubstring("eneral-purpose programming language");
        String second_article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
        }
        else if (Platform.getInstance().isIOS()){
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticle();
        }
        if (Platform.getInstance().isMw()){
            ArticlePageObject.waitForTitleElement();
//            assertEquals(
//                    "Wea are not on the same page after login",
//                    article_title,
//                    ArticlePageObject.getArticleTitle()
//            );
            ArticlePageObject.addArticlesToMySaved();
        }

        //Open my list and delete on of the articles


        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();

        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(first_article_title)

        ;

        //checking title of saved article
        Thread.sleep(1000);
        ArticlePageObject.ArticleLinkHasExpectedLink("C%2B%2B");
//        MyListsPageObject.clickOnSomeArticle(second_article_title);
//        ArticlePageObject.assertArticleHasExpectedTitle("Appium");

    }
}
