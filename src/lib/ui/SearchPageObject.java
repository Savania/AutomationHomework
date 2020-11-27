package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class SearchPageObject extends MainPageObject{
     protected static String
            SEARCH_INIT_ELEMENT ,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON ,
            SEARCH_RESULT_BY_SUBSTRING_TPL ,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_STRING_WITH_REQUEST,
            PAGE_LIST_ITEM_TITLE;

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }
    /*TEMPLATES METHOD*/
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHOD*/

    public void initSearchInput(){
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,"Cannot find and click search init elemnt", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,"Cannot find search input after clicking search init element");
        }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button",5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button still present",5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"cannot find and click search cancel button", 5);
    }

        public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(SEARCH_INPUT,search_line,"Cannot find and tap into search input", 5);
        }

        public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring"+substring);
        }


    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,"Cannot find and click search result with substring"+substring,10);
    }

    public int getAmountOfFoundArticles ()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by request",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element",15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT,"We supposed not to find any results");
    }

    public void assertSearchStringHasText(String expected_text)
    {
        this.assertElementHasText(SEARCH_STRING_WITH_REQUEST,"Cannot find expected text in search line", 5,expected_text);
    }

    public void waitForPageListItemTitleToAppear(){
        this.waitForElementPresent(PAGE_LIST_ITEM_TITLE, "Cannot find page list item title",5);
    }

    public void waitForPageListItemTitleToDisappear(){
        this.waitForElementNotPresent(PAGE_LIST_ITEM_TITLE, "Search page list item title",5);
    }




}
