package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends CommonMethods {

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
    public static WebElement addButton;

    @FindBy(xpath = "//div[@class='oxd-grid-2 orangehrm-full-width-grid']//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//i[1]")
    public static WebElement userRoleDropdown;

    @FindBy(xpath = "//div[@class='oxd-form-row user-password-row']")
    public static WebElement ESSoption;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    public static WebElement employeeNameSearchField;

    @FindBy(xpath = "//div[normalize-space()='Scott Hall']")
    public static WebElement clickScottHall;

    @FindBy(xpath = "//label[normalize-space()='Status']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text')]\n")
    public static WebElement statusDropdown;

    @FindBy(xpath = "//div[@role='option' and normalize-space()='Enabled']")
    public static WebElement enabledOption;

    @FindBy(xpath = "//label[normalize-space()='Username']/ancestor::div[contains(@class,'oxd-input-group')]//input\n")
    public static WebElement userNameField;

    @FindBy(xpath = "//label[normalize-space()='Password']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    public static WebElement passwordField;

    @FindBy(xpath = "//label[normalize-space()='Confirm Password']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    public static WebElement confirmPasswordField;

    @FindBy (xpath = "//button[@type='submit']")
    public static WebElement saveEmployeeInfoButton;



    public AdminPage(){
        PageFactory.initElements(driver,this);
    }
}
