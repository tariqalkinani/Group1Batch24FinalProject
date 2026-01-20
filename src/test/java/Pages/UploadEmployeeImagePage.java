package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Utils.CommonMethods.driver;

public class UploadEmployeeImagePage {

    @FindBy(xpath = "//*[@class='oxd-text oxd-text--p orangehrm-input-hint']")
    public WebElement errorMsg;
    @FindBy(xpath = "//*[@class='employee-image']")
    public WebElement empimage;
    @FindBy(xpath = "//*[@class='oxd-icon bi-plus']")
    public WebElement empAdd;





    public UploadEmployeeImagePage(){

        PageFactory.initElements(driver, this);
    }
}
