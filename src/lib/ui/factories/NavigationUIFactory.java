package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidNavigationUi;
import lib.ui.NavigationUI;
import lib.ui.iOS.iOSNavigationUi;

public class NavigationUIFactory {
    public static NavigationUI get(AppiumDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidNavigationUi(driver);
        }
        else
        {
            return new iOSNavigationUi(driver);
        }
    }

}
