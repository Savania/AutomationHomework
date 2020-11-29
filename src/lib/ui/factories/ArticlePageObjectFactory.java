package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidArticlePageObject;
import lib.ui.ArticlePajeObject;
import lib.ui.MainPageObject;
import lib.ui.iOS.iOSArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObjectFactory  {
    public static ArticlePajeObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidArticlePageObject(driver);
        }
        else{
            return new iOSArticlePageObject(driver);
        }
    }
}
