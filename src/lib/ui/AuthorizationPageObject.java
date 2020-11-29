package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{
    private static final String
        LOGIN_BUTTON="xpath://body/div/div/a[text()='Log in']",
        LOGIN_INPUT="css:input[name='wpName']",
        PASSWORD_INPUT="css:input[name='wpPassword']",
        SUBMIT_BUTTON ="css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthButton()
    {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button",5);


      //  this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button",5);
        if (Platform.getInstance().isMw()) {
            this.tryClickElementWithFewAttempts(
                    LOGIN_BUTTON,
                    "Cannot find and click auth button",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    LOGIN_BUTTON,
                    "Cannot find and click auth button",
                    5
            );
        }

    }

    public void enterLoginData(String login, String password)
    {
        this.waitForElementAndClear(LOGIN_INPUT,"Cannot clear a login input.",5);
        System.out.println("here we input login");

        this.waitForElementAndSendKeys(LOGIN_INPUT, login,"Cannot find and put a login input.",5);
        System.out.println("here did input login");
        this.waitForElementAndClear(PASSWORD_INPUT,"Cannot clear a password input.",5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password,"Cannot find and put a password input.",5);
    }

    public void submitForm()
    {
        this.waitForElementAndClick(SUBMIT_BUTTON,"Cannot find and click submit auth button",5);

    }
}