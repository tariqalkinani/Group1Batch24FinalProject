package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonMethods {

    @FindBy(xpath="//*[@name='username']")
    public WebElement usernameField;

    @FindBy(xpath="//*[@name='password']")
    public WebElement passwordField;

    @FindBy(xpath="//*[@type='submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@class='oxd-text oxd-text--p oxd-alert-content-text']")
    public WebElement errorMessageLoc;

    public LoginPage(){
        //The code we write will be automatically called when you create an object
        //Page Factory we use to initialize all the elements we use from Selenium and the driver is coming from CM
        PageFactory.initElements(driver,this);
    }

}
