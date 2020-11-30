package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static  String
        OPEN_NAVIGATION,
            MY_LISTS_LINK;

    public NavigationUI (RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openNavigation(){
        if (Platform.getInstance().isMw()){
            this.waitForElementAndClick(OPEN_NAVIGATION,"Cannot find and click open navigation",5);

        }
        else {
            System.out.println("Method openNavigetion does not use on this platform");
        }
    }

    public void clickMyLists() throws InterruptedException {
        if (Platform.getInstance().isMw()) {
            Thread.sleep(1000);
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
//            this.waitForElementAndClick(MY_LISTS_LINK,
//                    "Cannot find navigation button to My list",
//                    5);
        } else {
            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        }
    }


}
