package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS="ios";
    private static final String PLATFORM_ANDROID="android";
    protected AppiumDriver<AndroidElement> driver;
    private static String AppiumURL= "http://127.0.0.1:4723/wd/hub";


    @Override
    protected void setUp() throws Exception {

        super.setUp();
        driver=Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception{
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }


}
