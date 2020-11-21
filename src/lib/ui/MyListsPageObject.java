package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject  extends MainPageObject{


    public static final String
        FOLDER_BY_NAME_TPL = "//*[contains(@text,'{FOLDER_NAME}')]",
        ARTICLE_BY_TITLE_TPL = "//*[contains(@text,'{TITLE}')]",
        TITLE_NAME_OF_SMTH_BY_SUBSTRING = "//*[contains(@text,'{SUBSTRING}')]";

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

    public MyListsPageObject(AppiumDriver driver){
        super(driver);
    }




    public void openFolderByName(String name_of_folder){
        String folder_name_xpath=getFolderXPathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Can't find element '"+name_of_folder+"'",
                5
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath=getFolderXPathByName(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Saved article still preent with title" + article_title,
                15
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath=getFolderXPathByName(article_title);
        this.waitForElementPresent(
                By.xpath(article_xpath),
                "Cannot find seved article by title" + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath=getFolderXPathByName(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Can't swipe element 'Object-oriented programming language'"
        );
        this.waitForArticleToDisappearByTitle(article_xpath);
    }

    public void waitForElementPresentInMyList(String name_of_article)
    {
        String article=getXpathToFolderNameForSecondArticle(name_of_article);
        this.waitForElementPresent(By.xpath(article),"Cannot find article",15);
    }

    public void waitForElementNotPresentInMyList(String name_of_article)
    {
        String article=getXpathToFolderNameForSecondArticle(name_of_article);
        this.waitForElementNotPresent(By.xpath(article),"Search article wich was daleted",15);
    }

    public void clickOnSomeArticle(String name_of_article)
    {
        String article=getXpathToFolderNameForSecondArticle(name_of_article);
        this.waitForElementAndClick(
                By.xpath(article),
                "Cannot find article with name",
                15
        );
    }
}
