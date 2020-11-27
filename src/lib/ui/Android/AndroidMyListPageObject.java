package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class AndroidMyListPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[contains(@text,'{FOLDER_NAME}')]";
        ARTICLE_BY_TITLE_TPL = "xpath://*[contains(@text,'{TITLE}')]";
        TITLE_NAME_OF_SMTH_BY_SUBSTRING = "xpath://*[contains(@text,'{SUBSTRING}')]";
    }


    public AndroidMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
