package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidMyListPageObject;
import lib.ui.MyListsPageObject;
import lib.ui.iOS.iOSMyListPageObject;
import lib.ui.mobile_web.MWMyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListPageObjectFactory {
    public static MyListsPageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidMyListPageObject(driver);
        }
        else if (Platform.getInstance().isMw()){
            return new MWMyListPageObject(driver);
        }
        else
        {
            return new iOSMyListPageObject(driver);
        }
    }

}
