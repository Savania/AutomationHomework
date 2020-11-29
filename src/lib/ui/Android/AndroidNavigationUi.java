package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUi extends NavigationUI {
    static {
        MY_LISTS_LINK = "xpath://*[contains(@content-desc,'My lists')]";
    }

    public AndroidNavigationUi(RemoteWebDriver driver) {
        super(driver);
    }
}
