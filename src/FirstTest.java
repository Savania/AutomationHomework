import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.*;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;
    protected void setUp() throws Exception{
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }




    @Test
    public void testFirstSearchTest(){
        SearchPageObject SearchPageObject= new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch(){
        SearchPageObject SearchPageObject= new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

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

    //Домашнее задание №2
    @Test
    public void testSearchStringExistText(){

        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.assertSearchStringHasText("java");
    }

    //Домашнее задание №3
    @Test
    public  void testSearchStringCancel(){

        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForPageListItemTitleToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForPageListItemTitleToDisappear();
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


    @Test
    public void testAmountOfNotEmptySearch(){

        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results=SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results>0
        );
    }



    @Test
    public void testAmountOfEmptySearch(){

        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "lkdflsjflksd";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }


    @Test
    public void testChangeScreenOrientationSearchResult(){

        SearchPageObject SearchPageObject= new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePajeObject  ArticlePajeObject = new ArticlePajeObject(driver);
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
