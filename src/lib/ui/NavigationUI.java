package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{

    private static final String
        MY_LISTS_LINK="//*[contains(@content-desc,'My lists')]";

    public NavigationUI (AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyList()
    {
        this.waitForElementAndClick(
                By.xpath(MY_LISTS_LINK),
                "Can't find element 'My lists'",
                5
        );
    }


}
