import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

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



}
