package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage extends CommonMethods {

    @FindBy(xpath = "//*[@name='firstName']")
    public WebElement firstnamelocator;

    @FindBy(xpath = "//*[@name='middleName']")
    public WebElement middlenameloctor;

    @FindBy(xpath = "//*[@name='lastName']")
    public WebElement lastnamelocator;

    @FindBy(xpath = "//*[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    public WebElement saveButton;

    @FindBy(xpath = "(//*[@class='oxd-input oxd-input--active'])[2]")
    public static WebElement employeeIdButton;

    @FindBy(xpath = "//input[@name='firstName']")
    public static WebElement firstName;

    @FindBy(xpath = "//input[@name='lastName']")
    public static WebElement lastName;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active'][1]")
    public static WebElement employeeId;

    @FindBy(xpath = "//button[@type='submit']")
    public static WebElement submitButton;

    @FindBy(xpath = "//a[contains(text(),'Employee List')]")
    public static WebElement employeeList;


    public void enterEmployeeId(String empId) {
        employeeIdButton.clear();
        employeeIdButton.sendKeys(empId);
    }

    public AddEmployeePage(WebDriver driver){

        PageFactory.initElements(CommonMethods.driver, this);
    }
}
