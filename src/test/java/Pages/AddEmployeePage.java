package Pages;

import Utils.CommonMethods;
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
    public WebElement employeeIdButton;

    public AddEmployeePage(){

        PageFactory.initElements(driver, this);
    }
}
