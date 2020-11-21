package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePajeObject extends MainPageObject{

    private static final String
        TITLE="org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT ="//*[contains(@text,'View page in browser')]",
        OPTIONS_BUTTON="//*[contains(@content-desc,'More options')]",
        OPTIONS_ADD_TO_MY_LIST_BUTTON="//*[contains(@text,'Add to reading list')]",
        ADD_TO_MY_LIST_OVERLAY="//*[contains(@text,'Got it')]",
        MY_LIST_NAME_INPUT="org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON="android:id/button1",
        CLOSE_ARTICLE_BUTTON="//*[contains(@content-desc,'Navigate up')]";



    public ArticlePajeObject(AppiumDriver driver){
        super(driver);
    }

public WebElement waitForTitleElement(){
        return this.waitForElementPresent(By.id(TITLE),"Cannot find artice titleon page! ", 15);

}
public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        return  title_element.getAttribute("text");
}

public void swipeToFooter()
{
    this.swipeUpToFindElement(
            By.xpath(FOOTER_ELEMENT),
            "Cannot find the end of article",
            20
    );
}

public void addArticleToMyList(String name_of_folder)
{

    this.waitForElementAndClick(
            By.xpath(OPTIONS_BUTTON),
            "Can't find element 'More options'",
            5
    );


    this.waitForElementAndClick(
            By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
            "Can't find element 'Add to reading list'",
            5
    );

    this.waitForElementAndClick(
            By.xpath(ADD_TO_MY_LIST_OVERLAY),
            "Can't find element 'Got it'",
            5
    );


    this.waitForElementAndClear(
            By.id(MY_LIST_NAME_INPUT),
            "Can't find element 'input text'",
            5
    );


    this.waitForElementAndSendKeys(
            By.id(MY_LIST_NAME_INPUT),
            "Learning programming",
            "Can't send a text in input",
            5
    );


    this.waitForElementAndClick(
            By.id(MY_LIST_OK_BUTTON),
            "Can't find element 'android:id/button1'",
            5
    );
}

public void closeArticle()
{
    this.waitForElementAndClick(
            By.xpath(CLOSE_ARTICLE_BUTTON),
            "Can't find element 'Navigate up'",
            5
    );

}

}
