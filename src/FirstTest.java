import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class FirstTest {

    private AppiumDriver<AndroidElement> driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();


        capabilities.setCapability("platformName", "ANDROID");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/home/anna/Desktop/JavaAppiumAutomation2/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }



    //Тест с занятия 2 урока №1
    /*@Test
    public void firstTest() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Can't find 'SKIP'",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can't find element 'Search Wikipedia'",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "java",
                "Can't find element 'Search wikipedia",
                5
        );


        waitForElementPresent(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Can't find element 'oObject-oriented programming language'",
                15
        );
        }






        //Тест с занятия 2 №2
        @Test
        public void testCanselSearch()
        {
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'SKIP')]"),
                    "Can't find 'SKIP'",
                    5
            );

            waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_toolbar'",
                5
        );


            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "java",
                    "Can't find element 'Search wikipedia",
                    5
            );

            waitForElementAndClear(
                    By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_src_text')]"),
                    "Can't find element 'org.wikipedia:id/search_toolbar'",
                    5
            );
           waitForElementByXpathAndClick(
                    "//*[contains(@resource-id,'org.wikipedia:id/search_close_btn')]",
                    "Can't find element 'org.wikipedia:id/search_close_btn'",
                    5
        );

        waitForElementNotPresent(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_close_btn)]"),
                "org.wikipedia:id/search_close_btn'",
                15
        );

    }*/


    //Домашнее задание №2
    /*@Test
    public  void SearchStringExistText(){
       waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Can't find 'SKIP'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_toolbar'",
                5
        );


        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can't find element 'Search wikipedia",
                5
        );

        assertElementHasText(
           By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_src_text')]")   ,
           "Search string does not exist expected text",
                15,
                "java"


        );

    }*/

    //Домашнее задание №3
    @Test
    public  void SearchStringCancel(){

        //Нажимаем на поиск
        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_toolbar'",
                5
        );

        //Вводим какое-то слово
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can't find element 'Search wikipedia",
                5
        );


        //Проверяем наличие статей
        WebElement element=waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "There aren't some articles",
                20
        );


        //Закрываем поиск
        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_close_btn')]"),
                "Can't find element 'org.wikipedia:id/search_close_btn'",
                5
        );


        //Проверяем отсутствие статей
        waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Fail! There are some articles",
                15
        );



    }


    //Тема 3 урок 1
    @Test
    public  void TestSwipeArticle(){


        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_toolbar'",
                5
        );


        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "appium",
                "Can't find element 'Search wikipedia",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Appium')]"),
                "Can't find element 'Object-oriented programming language'",
                5
        );


        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text") ,
                "Fail! There are not some titles",
                15
        );

        swipeUpToFindElement(
                By.xpath("//*[contains(@text,'View page in browser')]"),
                "Cannot find the end of page",
                20
        );

    }



    @Test
    public void savFirstArticleToMyList(){
        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_toolbar'",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can't find element 'Search wikipedia",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Can't find element 'Object-oriented programming language'",
                5
        );


        WebElement title_element=waitForElementPresent(
                By.id("view_page_title_text"),
                "Can not find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@content-desc,'More options')]"),
                "Can't find element 'More options'",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Add to reading list')]"),
                "Can't find element 'Add to reading list'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Got it')]"),
                "Can't find element 'Got it'",
                5
        );


        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can't find element 'input text'",
                5
        );


        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Learning programming",
                "Can't send a text in input",
                5
        );


        waitForElementAndClick(
                By.id("android:id/button1"),
                "Can't find element 'android:id/button1'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@content-desc,'Navigate up')]"),
                "Can't find element 'Navigate up'",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@content-desc,'My lists')]"),
                "Can't find element 'My lists'",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Learning programming')]"),
                "Can't find element 'Learning programming'",
                5
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text,'object-oriented programming language')]"),
                "Can't find element 'Object-oriented programming language'",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[contains(@text,'object-oriented programming language')]"),
                "Can't swipe element 'Object-oriented programming language'"
        );
        waitForElementNotPresent(
                By.xpath("//*[contains(@text,'object-oriented programming language')]"),
                "Can't swipe element 'Object-oriented programming language'",
                15
        );
    }


    @Test
    public void testAmountOfNotEmptySearch(){

        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_toolbar'",
                5
        );

        String search_line = "Linkin park discography";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Can't find element 'Search wikipedia",
                5
        );


        String searchResultLocator="//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        waitForElementPresent(
                By.xpath(searchResultLocator),
                "Cannot find anything by request"+search_line,
                15
        );

        int amount_of_search_results=getAmountOfElements(
                By.xpath(searchResultLocator)
        );

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results>0
        );


    }



    @Test
    public void testAmountOfEmptySearch(){
        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_toolbar'",
                5
        );

        String search_line = "sfkjflskslkfjs";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Can't find element 'Search wikipedia",
                5
        );


        String searchResultLocator="//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String empty_result_label= "//*[contains(@text,'No results found')]";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Empty_result_label did not find",
                15

        );

        assertElementNotPresent(
                By.xpath(searchResultLocator),
                "We've find some results "+search_line

        );


    }


    @Test
    public void TestChangeScreenOrientationSearchResult(){

        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_toolbar'",
                5
        );

        String search_line = "java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Can't find element 'Search ...",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Can't find element 'Object-oriented programming language'",
                5
        );

        String title_before_rotation = waitForElementAndGetAttribute(
          By.id("org.wikipedia:id/view_page_title_text"),
          "text",
          "Cant find article title",
          15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cant find article title",
                15
        );

        Assert.assertEquals(
                "Article title was changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cant find article title",
                15
        );

        Assert.assertEquals(
                "Article title was changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );





    }



    @Test
    public void testCheckSearchArticleInBackground(){
        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_toolbar'",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can't find element 'Search wikipedia",
                5
        );


        waitForElementPresent(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Can't find element 'Object-oriented programming language'",
                5
        );


        driver.runAppInBackground(Duration.ofSeconds(3));

        waitForElementPresent(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Can't find element 'Object-oriented programming language' after background",
                5
        );





    }




    //Домашнее задание №3 ex 5
    @Test
    public void testAddTwoArticlesAndDelOne(){

        //Add first article
        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_tcontainer'",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can't find element 'Search ...",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Can't find article 'Object-oriented programming language'",
                5
        );


        waitForElementPresent(
                By.id("view_page_title_text"),
                "Can not find article title in java",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@content-desc,'More options')]"),
                "Can't find element 'More options' in article about java",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Add to reading list')]"),
                "Can't find element 'Add to reading list' in article about java",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Got it')]"),
                "Can't click element 'Got it'",
                5
        );


        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can't clear element 'input text'",
                5
        );


        String folder_java="Learning programming";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                folder_java,
                "Can't send a text in input",
                5
        );


        waitForElementAndClick(
                By.id("android:id/button1"),
                "Can't click to the  element 'android:id/button1'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@content-desc,'Navigate up')]"),
                "Can't find element 'Navigate up'",
                5
        );



        //Add second article
        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_toolbar'",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "appium",
                "Can't find element 'Search ...",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Appium')]"),
                "Can't find element 'Appium' in article title",
                5
        );


        waitForElementPresent(
                By.id("view_page_title_text"),
                "Can not find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@content-desc,'More options')]"),
                "Can't find element 'More options'",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Add to reading list')]"),
                "Can't find element 'Add to reading list'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Learning programming')]"),
                "Can't find element 'Learning programming'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@content-desc,'Navigate up')]"),
                "Can't find element 'Navigate up'",
                5
        );



        //Go to favourite
        waitForElementAndClick(
                By.xpath("//*[contains(@content-desc,'My lists')]"),
                "Can't find element 'My lists'",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Learning programming')]"),
                "Can't find element 'Learning programming'",
                5
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text,'object-oriented programming language')]"),
                "Can't find element 'Object-oriented programming language'",
                5
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text,'Appium')]"),
                "Can't find element 'Appium'",
                5
        );

        //delete one of article


        swipeElementToLeft(
                By.xpath("//*[contains(@text,'Appium')]"),
                "Can't swipe element 'Appium'"
        );
        waitForElementPresent(
                By.xpath("//*[contains(@text,'object-oriented programming language')]"),
                "Can't present element 'Object-oriented programming language'",
                15
        );
        waitForElementNotPresent(
                By.xpath("//*[contains(@text,'Appium')]"),
                "Present element 'Appium'",
                15
        );


        //Check name of saving article
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'object-oriented programming language')]"),
                "Can't click element 'Object-oriented programming language'",
                15
        );

        assertElementHasText(
                By.id("org.wikipedia:id/view_page_title_text")   ,
                "Search string does not exist expected text  'Java (programming language)'",
                15,
                "Java (programming language)"


        );








    }

    //Домашнее задание №3 ex 6
    @Test
    public void assertElementPresent(){
        waitForElementAndClick(
                By.xpath("//*[contains(@resource-id,'org.wikipedia:id/search_container')]"),
                "Can't find element 'org.wikipedia:id/search_tcontainer'",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can't find element 'Search ...",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Can't find article 'Object-oriented programming language'",
                5
        );

        assertElementNotPresent(
                By.id("view_page_title_text"),
                "Can't find title"
        );


    }







    //Испольуемые методы

    private WebElement waitForElementPresent(By by, String error_message, long timeOutSeconds){
        WebDriverWait wait =new WebDriverWait(driver, timeOutSeconds);
        wait.withMessage(error_message+"\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }


    private WebElement waitForElementPresent(By by, String error_message){
        return waitForElementPresent(by,error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeOutSeconds){
        WebElement element =waitForElementPresent(by,error_message,timeOutSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutSeconds){
        WebElement element =waitForElementPresent(by,error_message,timeOutSeconds);
        element.sendKeys(value);
        return element;
    }


    private boolean waitForElementNotPresent (By by, String error_message, long timeOutSeconds){
        WebDriverWait wait =new WebDriverWait(driver, timeOutSeconds);
        wait.withMessage(error_message+"\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeOutSeconds){
        WebElement element =waitForElementPresent(by,error_message,timeOutSeconds);
        element.clear();
        return element;
    }


    private WebElement assertElementHasText(By by, String error_message, long timeOutSeconds, String expected_text){
        WebElement title_element=waitForElementPresent(
                by,
                "error in finding element",
                timeOutSeconds
        );
        //System.out.println("title_element "+title_element);
        String article_title = title_element.getAttribute("text");
       // System.out.println("article_title "+article_title);
        Assert.assertEquals(
                error_message,
                expected_text,
                article_title
        );
         return title_element;
    }


    protected void swipeUp(long timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x =(int) (size.width / 2);
        int start_y = (int) (size.height *0.8);
        int end_y = (int) (size.height *0.2);
        action.press(PointOption.point(x, start_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe))).moveTo(PointOption.point(x, end_y)).release().perform();
    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
        int already_swiped=0;
        while (driver.findElements(by).size()==0){
            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping up \n"+ error_message,0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }


    protected void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(by, error_message,10);
        int left_x = element.getLocation().getX();
        int right_x = left_x+element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y+element.getSize().getHeight();
        int middle_y = (upper_y+lower_y)/2;


        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(right_x, middle_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(150))).moveTo(PointOption.point(left_x, middle_y)).release().perform();


    }

    private int getAmountOfElements(By by){
        List elements= driver.findElements(by);
        return elements.size();
    }


    private void assertElementNotPresent(By by, String error_message){
        int emount_of_elements= getAmountOfElements(by);
        if (emount_of_elements>0){
            String defoult_message= "An element '"+ by.toString()+ "' supposed to be not present";
            throw new AssertionError(defoult_message+" "+error_message);
        }
    }


    private String waitForElementAndGetAttribute (By by, String attribute, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }



}
