package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidSearchPageObject;
import lib.ui.SearchPageObject;
import lib.ui.iOS.iOSSearchPageObject;
import lib.ui.mobile_web.MWSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.regex.Pattern;

public class SearchPageObjectFactory {
    public static SearchPageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid())
        {
            return new AndroidSearchPageObject(driver);
        }
        else if (Platform.getInstance().isMw())
        {
            return new MWSearchPageObject(driver);
        }
        else
        {
            return new iOSSearchPageObject(driver);
        }
    }

}
