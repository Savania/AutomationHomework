package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase extends TestCase {


    protected RemoteWebDriver driver;



    @Override
    protected void setUp() throws Exception {

        super.setUp();
        driver=Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.openWikuWebPageForMobileWeb();
    }

    @Override
    protected void tearDown() throws Exception{
        driver.quit();
        super.tearDown();

    }

    protected void rotateScreenPortrait()
    {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver=(AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
        else {
            System.out.println("Method rotate screen portrate does nothing for platform"+Platform.getInstance().getPlatformVar());
        }

    }

    protected void rotateScreenLandscape()
    {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver=(AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        }
        else {
            System.out.println("Method rotate screen landscape does nothing for platform"+Platform.getInstance().getPlatformVar());
        }

    }

    protected void openWikuWebPageForMobileWeb()
    {
        if (Platform.getInstance().isMw())
        {
            driver.get("https://en.m.wikipedia.org");
        }
        else
        {
            System.out.println("Method does nothing for platform"+Platform.getInstance().getPlatformVar());
        }
    }


    protected void backgroundApp(int seconds)
    {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver=(AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        }
        else {
            System.out.println("Method run in background  does nothing for platform"+Platform.getInstance().getPlatformVar());
        }

    }


}
