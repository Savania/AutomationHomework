package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[contains(@text,'{FOLDER_NAME}')]";
                ARTICLE_BY_TITLE_TPL = "xpath://*[contains(@text,'{TITLE}')]";
                TITLE_NAME_OF_SMTH_BY_SUBSTRING = "xpath://*[contains(@text,'{SUBSTRING}')]";
    }

    public iOSMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
