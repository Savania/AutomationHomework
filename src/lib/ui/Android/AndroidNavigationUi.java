package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUi extends NavigationUI {
    static {
        MY_LISTS_LINK = "xpath://*[contains(@content-desc,'My lists')]";
    }

    public AndroidNavigationUi(AppiumDriver driver) {
        super(driver);
    }
}
