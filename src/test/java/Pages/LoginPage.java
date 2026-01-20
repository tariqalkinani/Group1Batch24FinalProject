package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonMethods {

    @FindBy(xpath="//input[@placeholder='Username']")
    //input[@placeholder='Username']
    public WebElement usernameField;

    @FindBy(xpath="//*[@name='password']")
    public WebElement passwordField;

    @FindBy(xpath="//*[@type='submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@class='oxd-text oxd-text--p oxd-alert-content-text']")
    public WebElement errorMessageLoc;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

}
