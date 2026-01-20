package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EditEmployeePage extends CommonMethods {

    @FindBy(xpath = "//*[@placeholder='Type for hints...'][1]")
    public WebElement employeeName;
    @FindBy(xpath = "//*[text()='Scott '][1]")
    public WebElement empName;
    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    public WebElement savebutton;
    @FindBy(xpath ="//label[text()='Employee Id']/following::input[1]")
    public WebElement employeeId;
    @FindBy(xpath = "//span[@class='oxd-radio-input oxd-radio-input--active --label-right oxd-radio-input'][2]")
    public WebElement genderlocator;
    @FindBy(xpath = "(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]")
    public WebElement Nationalitydropdown;
    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[1]")
    public WebElement NationalityContainer;
    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[2]")
    public WebElement MaritalContainer;
    @FindBy(xpath = "(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[2]")
    public WebElement MaritalStatusdropdown;
    @FindBy(xpath = "//div[text()='American']")
    public WebElement American;
    @FindBy(xpath = "//div[@class='oxd-select-text-input'and text()='Married']")
    public WebElement Married;
    @FindBy(xpath = "//a[text()='Personal Details']")
    public WebElement personalDetailsHeader;


    public EditEmployeePage(){

        PageFactory.initElements(driver, this);
    }
}
