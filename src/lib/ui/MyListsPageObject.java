package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject  extends MainPageObject{


    protected static  String
        FOLDER_BY_NAME_TPL ,
        ARTICLE_BY_TITLE_TPL,
        REMOVE_FROM_SAVED_BUTTON,
        TITLE_NAME_OF_SMTH_BY_SUBSTRING;

    private static String getFolderXPathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    /*TEMPLATES METHOD*/
    private static String getXpathToFolderNameForSecondArticle(String substring) {
        return TITLE_NAME_OF_SMTH_BY_SUBSTRING.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHOD*/

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }


    public MyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }




    public void openFolderByName(String name_of_folder){
        String folder_name_xpath=getFolderXPathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Can't find element '"+name_of_folder+"'",
                5
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath=getFolderXPathByName(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still preent with title" + article_title,
                15
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath=getFolderXPathByName(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find seved article by title" + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath=getFolderXPathByName(article_title);
        if ((Platform.getInstance().isAndroid()) ||(Platform.getInstance().isIOS())){
            this.swipeElementToLeft(
                    article_xpath,
                    "Can't swipe element 'Object-oriented programming language'"
            );
        }
        else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }
        if (Platform.getInstance().isMw()){
            driver.navigate().refresh();
        }
        this.waitForArticleToDisappearByTitle(article_xpath);
    }

    public void waitForElementPresentInMyList(String name_of_article)
    {
        String article=getXpathToFolderNameForSecondArticle(name_of_article);
        this.waitForElementPresent(article,"Cannot find article",15);
    }

    public void waitForElementNotPresentInMyList(String name_of_article)
    {
        String article=getXpathToFolderNameForSecondArticle(name_of_article);
        this.waitForElementNotPresent(article,"Search article wich was daleted",15);
    }

    public void clickOnSomeArticle(String name_of_article)
    {
        String article=getXpathToFolderNameForSecondArticle(name_of_article);
        this.waitForElementAndClick(
                article,
                "Cannot find article with name",
                15
        );
    }
}
