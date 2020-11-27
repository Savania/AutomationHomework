package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@resource-id,'org.wikipedia:id/search_container')]";
        SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]";
        SEARCH_CANCEL_BUTTON = "is:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[contains(@text,'No results found')]";
        SEARCH_STRING_WITH_REQUEST = "xpath://*[contains(@resource-id,'org.wikipedia:id/search_src_text')]";
        PAGE_LIST_ITEM_TITLE = "id:org.wikipedia:id/page_list_item_title";
    }
    public iOSSearchPageObject (AppiumDriver driver){
        super(driver);
    }
}
