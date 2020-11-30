package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE ,
            FOOTER_ELEMENT ,
            OPTIONS_BUTTON ,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTION_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON ,
            CLOSE_ARTICLE_BUTTON ,
            TITLE_NAME_OF_SMTH_BY_SUBSTRING ,
            ARTICLE_LINK;




    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
    /*TEMPLATES METHOD*/
    private static String getXpathToFolderNameForSecondArticle(String substring) {
        return TITLE_NAME_OF_SMTH_BY_SUBSTRING.replace("{SUBSTRING}", substring);
    }
    private static String getXpathToArticleLink(String substring) {
        return ARTICLE_LINK.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHOD*/

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find artice titleon page! ", 15);

    }

    public WebElement assertArticleHasExpectedTitle(String title) {
        return this.assertElementHasText(TITLE, "Cannot find artice titleon page! ", 15, title);

    }


//    public WebElement assertArticleLinkHasExpectedLink(String title) {
//        String link= getXpathToArticleLink(title);
//        System.out.println(link);
//        return this.assertElementHasText(link, "Cannot find artice titleon page! ", 15, title);
//
//    }
    public void ArticleLinkHasExpectedLink(String title){
        String link= getXpathToArticleLink(title);
        this.waitForElementPresent(link, "cannot find article with link",15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        }
        else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        }
        else {
            return title_element.getText();
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    20
            );
        }
        else if (Platform.getInstance().isIOS())
        {
            //
        }

        else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        }
    }

    public void addArticleToMyList(String name_of_folder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Can't find element 'More options'",
                5
        );


        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can't find element 'Add to reading list'",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Can't find element 'Got it'",
                5
        );


        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Can't find element 'input text'",
                5
        );


        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                "Learning programming",
                "Can't send a text in input",
                5
        );


        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Can't find element 'android:id/button1'",
                5
        );
    }

    public  void addArticlesToMySaved()
    {
        if (Platform.getInstance().isMw()){
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,"Canot find option to add article to reading list",5);
    }

    public void removeArticleFromSavedIfItAdded (){
        if (this.isElementPresent(OPTION_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(
                    OPTION_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    5
            );
            this.waitForElementPresent(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to an article to saved list after removing from this lis before"
            );
        }
    }

    public void closeArticle() {
        if ((Platform.getInstance().isAndroid())|| (Platform.getInstance().isIOS())){
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Can't find element 'Navigate up'",
                    5
            );
        }
        else
        {
            System.out.println("Method closeArticle cant use in this platform");
        }


    }


    public void addSecondArticleToMyList(String name_of_folder) {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Can't find element 'More options'",
                5
        );


        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can't find element 'Add to reading list'",
                5
        );

        String name_folder_xpath = getXpathToFolderNameForSecondArticle(name_of_folder);
        this.waitForElementAndClick(
                name_folder_xpath,
                "Can't find folder in my list",
                15
        );
    }


    public void assertTitlePresentInArticle()
    {
        this.assertElementPresent(
                TITLE,
                "Can't find title"
        );
    }




}
