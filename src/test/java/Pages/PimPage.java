package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PimPage extends CommonMethods {

    @FindBy(xpath = "//label[contains(normalize-space(),'Employee Name')]/ancestor::div[contains(@class,'oxd-input-group')]//input[@placeholder='Type for hints...']\n")
    public static WebElement EmployeeNameBox;

    @FindBy(xpath = "//div[contains(@class,'oxd-autocomplete-dropdown')]//div[contains(normalize-space(),'Martiness')]\n")
    public static WebElement clickKevin;

    @FindBy(xpath = "//label[contains(normalize-space(),'Supervisor')]/ancestor::div[contains(@class,'oxd-input-group')]//input[@placeholder='Type for hints...']\n")
    public static WebElement SupervisorBox;

    @FindBy(xpath = "//div[contains(@class,'oxd-autocomplete-dropdown')]//div[normalize-space()='John Smith Doe']\n")
    public static WebElement clickJohnDoe;

    @FindBy(xpath = "//label[contains(normalize-space(),'Employee Id')]/ancestor::div[contains(@class,'oxd-input-group')]//input\n")
    public static WebElement employeeIDbox;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    public static WebElement searchButton;

    @FindBy(xpath = "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']")
    public static WebElement employeeInfoBoxToClick;

    @FindBy(xpath = "//label[text()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input\n")
    public static WebElement employeeIDboxPROOF;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span']")
    public static WebElement noRecordFoundText;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-row')]")
    public WebElement employeeRow;

    @FindBy(xpath = "(//div[contains(@class,'oxd-table-row')]//div[contains(@class,'oxd-table-cell')])[2]")
    public WebElement employeeIdCell;

    public PimPage() {
        PageFactory.initElements(driver, this);
    }
}
