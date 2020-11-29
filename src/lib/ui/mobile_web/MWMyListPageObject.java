package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[contains(@text,'{FOLDER_NAME}')]";
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]";
        TITLE_NAME_OF_SMTH_BY_SUBSTRING = "xpath://*[contains(@text,'{SUBSTRING}')]";
        REMOVE_FROM_SAVED_BUTTON="xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]/../../div[contains(@class,'watched')]";

    }

    public MWMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
