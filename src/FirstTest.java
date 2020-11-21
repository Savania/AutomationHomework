import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import java.time.Duration;

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
    public  void SearchStringExistText(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Can't find 'SKIP'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_toolbar'",
                5
        );


        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can't find element 'Search wikipedia",
                5
        );

        MainPageObject.assertElementHasText(
           By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_src_text')]")   ,
           "Search string does not exist expected text",
                15,
                "java"


        );

    }

    //Домашнее задание №3
    @Test
    public  void testSearchStringCancel(){

        //Нажимаем на поиск
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_toolbar'",
                5
        );

        //Вводим какое-то слово
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can't find element 'Search wikipedia",
                5
        );


        //Проверяем наличие статей
        WebElement element=MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "There aren't some articles",
                20
        );


        //Закрываем поиск
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_close_btn')]"),
                "Can't find element 'org.wikipedia:id/search_close_btn'",
                5
        );


        //Проверяем отсутствие статей
        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Fail! There are some articles",
                15
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
        SearchPageObject.asserThereIsNoResultOfSearch();

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

        //Add first article
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_tcontainer'",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can't find element 'Search ...",
                5
        );


        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Can't find article 'Object-oriented programming language'",
                5
        );


        MainPageObject.waitForElementPresent(
                By.id("view_page_title_text"),
                "Can not find article title in java",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@content-desc,'More options')]"),
                "Can't find element 'More options' in article about java",
                5
        );


        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Add to reading list')]"),
                "Can't find element 'Add to reading list' in article about java",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Got it')]"),
                "Can't click element 'Got it'",
                5
        );


        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can't clear element 'input text'",
                5
        );


        String folder_java="Learning programming";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                folder_java,
                "Can't send a text in input",
                5
        );


        MainPageObject.waitForElementAndClick(
                By.id("android:id/button1"),
                "Can't click to the  element 'android:id/button1'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@content-desc,'Navigate up')]"),
                "Can't find element 'Navigate up'",
                5
        );



        //Add second article
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_toolbar'",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "appium",
                "Can't find element 'Search ...",
                5
        );


        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Appium')]"),
                "Can't find element 'Appium' in article title",
                5
        );


        MainPageObject.waitForElementPresent(
                By.id("view_page_title_text"),
                "Can not find article title",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@content-desc,'More options')]"),
                "Can't find element 'More options'",
                5
        );


        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Add to reading list')]"),
                "Can't find element 'Add to reading list'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Learning programming')]"),
                "Can't find element 'Learning programming'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@content-desc,'Navigate up')]"),
                "Can't find element 'Navigate up'",
                5
        );



        //Go to favourite
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@content-desc,'My lists')]"),
                "Can't find element 'My lists'",
                5
        );


        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Learning programming')]"),
                "Can't find element 'Learning programming'",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text,'object-oriented programming language')]"),
                "Can't find element 'Object-oriented programming language'",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text,'Appium')]"),
                "Can't find element 'Appium'",
                5
        );

        //delete one of article


        MainPageObject.swipeElementToLeft(
                By.xpath("//*[contains(@text,'Appium')]"),
                "Can't swipe element 'Appium'"
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text,'object-oriented programming language')]"),
                "Can't present element 'Object-oriented programming language'",
                15
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[contains(@text,'Appium')]"),
                "Present element 'Appium'",
                15
        );


        //Check name of saving article
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'object-oriented programming language')]"),
                "Can't click element 'Object-oriented programming language'",
                15
        );

        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/view_page_title_text")   ,
                "Search string does not exist expected text  'Java (programming language)'",
                15,
                "Java (programming language)"


        );








    }

    //Домашнее задание №3 ex 6
    @Test
    public void testAssertElementPresent(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_tcontainer'",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can't find element 'Search ...",
                5
        );


        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Can't find article 'Object-oriented programming language'",
                5
        );

        MainPageObject.assertElementPresent(
                By.id("view_page_title_text"),
                "Can't find title"
        );


    }


}
