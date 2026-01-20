package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateContactPage extends CommonMethods {

    @FindBy(xpath="//label[normalize-space()='Street 1']/following::input[1]")
    public WebElement addy1Field;

    @FindBy(xpath="//label[normalize-space()='Street 2']/following::input[1]")
    public  WebElement addy2Field;

    @FindBy(xpath="//label[normalize-space()='City']/following::input[1]")
    public  WebElement cityField;

    @FindBy(xpath="//label[normalize-space()='State/Province']/following::input[1]")
    public  WebElement stateField;

    @FindBy(xpath="//label[normalize-space()='Zip/Postal Code']/following::input[1]")
    public WebElement zipField;

    @FindBy(xpath="//label[normalize-space()='Country']/following::input[1]")
    public WebElement countryField;


    @FindBy(xpath="//label[text()='Home']/following::input[1]")
    public WebElement homePhone;

    @FindBy(xpath="//label[text()='Mobile']/following::input[1]")
    public WebElement mobilePhone;

    @FindBy(xpath="//label[text()='Work']/following::input[1]")
    public WebElement workPhone;

    @FindBy(xpath="//label[text()='Work Email']/following::input[1]")
    public WebElement workEmail;

    @FindBy(xpath="//label[text()='Other Email']/following::input[1]")
    public WebElement otherEmail;

    @FindBy(xpath="//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    public WebElement saveButton;



    public UpdateContactPage(){
        PageFactory.initElements(driver, this);
    }



}
