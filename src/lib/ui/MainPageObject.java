package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver){
        this.driver=driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeOutSeconds){
        By by= this.getLocatorByString(locator);
        WebDriverWait wait =new WebDriverWait(driver, timeOutSeconds);
        wait.withMessage(error_message+"\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }


    public WebElement waitForElementPresent(String locator, String error_message){
        return waitForElementPresent(locator,error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeOutSeconds){
        WebElement element =waitForElementPresent(locator,error_message,timeOutSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeOutSeconds){
        WebElement element =waitForElementPresent(locator,error_message,timeOutSeconds);
        element.sendKeys(value);
        return element;
    }


    public boolean waitForElementNotPresent (String locator, String error_message, long timeOutSeconds){
        By by= this.getLocatorByString(locator);
        WebDriverWait wait =new WebDriverWait(driver, timeOutSeconds);
        wait.withMessage(error_message+"\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeOutSeconds){
        WebElement element =waitForElementPresent(locator,error_message,timeOutSeconds);
        element.clear();
        return element;
    }


    public WebElement assertElementHasText(String locator, String error_message, long timeOutSeconds, String expected_text){
        WebElement title_element=waitForElementPresent(
                locator,
                "error in finding element",
                timeOutSeconds
        );
        //System.out.println("title_element "+title_element);
        String article_title = title_element.getAttribute("text");
        // System.out.println("article_title "+article_title);
        Assert.assertEquals(
                error_message,
                expected_text,
                article_title
        );
        return title_element;
    }


    public void swipeUp(long timeOfSwipe){
        if (driver instanceof AppiumDriver){
            AppiumDriver driver=(AppiumDriver) this.driver;
            TouchAction action = new TouchAction(driver);
            Dimension size = driver.manage().window().getSize();
            int x =(int) (size.width / 2);
            int start_y = (int) (size.height *0.8);
            int end_y = (int) (size.height *0.2);
            action.press(PointOption.point(x, start_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe))).moveTo(PointOption.point(x, end_y)).release().perform();

        }
        else {
            System.out.println("Method swipeUp does nothing for platform"+ Platform.getInstance().getPlatformVar());
        }
      }

    public void swipeUpQuick(){
        swipeUp(200);
    }

    public void scrollWebPageUp()
    {
        if (Platform.getInstance().isMw()){
            JavascriptExecutor JSExecuter = (JavascriptExecutor) driver;
            JSExecuter.executeScript("window.scrollBy(0,250)");

        }
        else {
            System.out.println("Method scrollWebPageUp does nothing for platform"+ Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        WebElement element=this.waitForElementPresent(locator,error_message);
        while (!this.isElementLocatedOnTheScreen(locator))
        {
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped>max_swipes){
                Assert.assertTrue(error_message,element.isDisplayed());
            }
        }
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes){
        By by= this.getLocatorByString(locator);
        int already_swiped=0;
        while (driver.findElements(by).size()==0){
            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "Cannot find element by swiping up \n"+ error_message,0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }


    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y =  this.waitForElementPresent(locator, "Cannot find element on the page", 7).getLocation().getY();
        if (Platform.getInstance().isMw()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("return window .pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public void swipeElementToLeft(String locator, String error_message){

        if (driver instanceof AppiumDriver){
            AppiumDriver driver=(AppiumDriver) this.driver;
            WebElement element = waitForElementPresent(locator, error_message,10);
            int left_x = element.getLocation().getX();
            int right_x = left_x+element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y+element.getSize().getHeight();
            int middle_y = (upper_y+lower_y)/2;


            TouchAction action = new TouchAction(driver);
            action.press(PointOption.point(right_x, middle_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(150))).moveTo(PointOption.point(left_x, middle_y)).release().perform();

        }
        else {
            System.out.println("Method run in background  does nothing for platform"+Platform.getInstance().getPlatformVar());
        }


    }

    public int getAmountOfElements(String locator){
        By by= this.getLocatorByString(locator);
        List elements= driver.findElements(by);
        return elements.size();
    }

    public boolean isElementPresent (String locator){
        return getAmountOfElements(locator)>0;
    }

    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts){
        int current_attempts=0;
        boolean need_more_attempts = true;
        while (need_more_attempts){
            try {
                this.waitForElementAndClick(locator, error_message,1);
                need_more_attempts=false;
            } catch (Exception e){
                if (current_attempts>amount_of_attempts){
                    this.waitForElementAndClick(locator,error_message,5);
                }
            }
            ++ current_attempts;
        }
    }




    public void assertElementNotPresent(String locator, String error_message){
        By by= this.getLocatorByString(locator);
        int amount_of_elements= getAmountOfElements(locator);
        if (amount_of_elements>0){
            String default_message= "An element '"+ by.toString()+ "' supposed to be not present";
            throw new AssertionError(default_message+" "+error_message);
        }
    }


    public String waitForElementAndGetAttribute (String locator, String attribute, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void assertElementPresent(String locator, String error_message){
        int amount_of_elements= getAmountOfElements(locator);
        if (amount_of_elements==0){
            String default_message= "An element '"+ locator+ "' supposed to be not present";
            throw new AssertionError(default_message+" "+error_message);
        }
    }


    private By getLocatorByString(String locator_with_type)
    {
        String [] exploded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type=exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")){
            return By.xpath(locator);
        } else if (by_type.equals("id")){
            return By.id(locator);
        } else if (by_type.equals("css")){
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Cannt get type of locator. Locator: "+locator_with_type);
        }
    }



}
