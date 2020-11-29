package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidNavigationUi;
import lib.ui.NavigationUI;
import lib.ui.iOS.iOSNavigationUi;
import lib.ui.mobile_web.MWNavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIFactory {
    public static NavigationUI get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidNavigationUi(driver);
        }
        else if (Platform.getInstance().isMw()){
            return new MWNavigationUI(driver);
        }
        else
        {
            return new iOSNavigationUi(driver);
        }
    }

}
