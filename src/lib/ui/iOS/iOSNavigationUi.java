package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iOSNavigationUi extends NavigationUI {
    static {
        MY_LISTS_LINK = "xpath://*[contains(@content-desc,'My lists')]";
    }

    public iOSNavigationUi(AppiumDriver driver) {
        super(driver);
    }
}
