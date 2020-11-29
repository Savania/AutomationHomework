package lib.ui.iOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUi extends NavigationUI {
    static {
        MY_LISTS_LINK = "xpath://*[contains(@content-desc,'My lists')]";
    }

    public iOSNavigationUi(RemoteWebDriver driver) {
        super(driver);
    }
}
