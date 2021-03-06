package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    private static final String PLATFORM_IOS="ios";
    private static final String PLATFORM_ANDROID="android";
    private static final String PLATFORM_MOBILE_WEB="mobile_web";
    private static final String APPIUM_URL= "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;
    private Platform(){
    }
    public static Platform getInstance()
    {
        if (instance==null){
            instance=new Platform();
        }
        return instance;
    }


    public RemoteWebDriver getDriver() throws Exception
    {
        URL URL= new URL(APPIUM_URL);
        if (this.isAndroid())
        {
            return new AndroidDriver(URL, getAndroidDesiredCapabilities());
        }
        else if (this.isIOS())
        {
            return new IOSDriver(URL, getIOSDesiredCapabilities());
        }
        else if (this.isMw())
            {
                return new ChromeDriver(this.GetMwChromeOptions());
            }
        else {
            throw new Exception("Cannoct detect type of the driver "+this.getPlatformVar());
        }

    }
     public boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS(){
        return isPlatform(PLATFORM_IOS);
    }

    public boolean isMw() {return isPlatform(PLATFORM_MOBILE_WEB);}

    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "ANDROID");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/home/anna/Desktop/JavaAppiumAutomation2/apks/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "11.3");
        //Путь до апк
        capabilities.setCapability("app", "/home/anna/Desktop/JavaAppiumAutomation2/apks/org.wikipedia.apk");
        return capabilities;
    }

    private ChromeOptions GetMwChromeOptions()
    {
        Map<String , Object> deviceMetrics = new HashMap<String , Object>();
        deviceMetrics.put("width",360);
        deviceMetrics.put("heiht",640);
        deviceMetrics.put("picelRatio",3.0);

        Map<String , Object> mobilEmulation = new HashMap<String , Object>();
        mobilEmulation.put("deviceMetrcs", deviceMetrics);
        mobilEmulation.put("userAgent","Mozilla/5.0 (Linux; Android 6.0; Android SDK built for x86 Build/MASTER; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/44.0.2403.119 Mobile Safari/537.36");

        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");

        return chromeOptions;
    }

    private boolean isPlatform (String my_platform)
    {
        String platform= this.getPlatformVar();
        return my_platform.equals(platform);
    }
    public String getPlatformVar(){
        return System.getenv("PLATFORM");
    }
}
