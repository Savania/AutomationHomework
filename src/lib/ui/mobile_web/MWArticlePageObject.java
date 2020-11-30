package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_BUTTON = "xpath://*[contains(@content-desc,'More options')]";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions li#page-actions-watch";
        ADD_TO_MY_LIST_OVERLAY = "xpath://*[contains(@text,'Got it')]";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "id:android:id/button1";
        CLOSE_ARTICLE_BUTTON = "xpath://*[contains(@content-desc,'Navigate up')]";
        TITLE_NAME_OF_SMTH_BY_SUBSTRING = "xpath://*[contains(@text,'{SUBSTRING}')]";
        OPTION_REMOVE_FROM_MY_LIST_BUTTON="css:#page-actions-watch a#ca-watch.watched";
        ARTICLE_LINK="xpath://a[contains(@href,'/wiki/{SUBSTRING}')]";
    }
    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
