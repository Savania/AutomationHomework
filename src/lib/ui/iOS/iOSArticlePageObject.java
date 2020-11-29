package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePajeObject;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePajeObject {
    static {
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "xpath://*[contains(@text,'View page in browser')]";
        OPTIONS_BUTTON = "xpath://*[contains(@content-desc,'More options')]";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[contains(@text,'Add to reading list')]";
        ADD_TO_MY_LIST_OVERLAY = "xpath://*[contains(@text,'Got it')]";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "id:android:id/button1";
        CLOSE_ARTICLE_BUTTON = "xpath://*[contains(@content-desc,'Navigate up')]";
        TITLE_NAME_OF_SMTH_BY_SUBSTRING = "xpath://*[contains(@text,'{SUBSTRING}')]";
    }
    public iOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }


}
