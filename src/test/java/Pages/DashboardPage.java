package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends CommonMethods {

    @FindBy(xpath = "(//*[text()='PIM'])[1]")
    public WebElement pimOption;

    @FindBy(xpath = "//*[text()='Employee List']")
    public WebElement empListOption;

    @FindBy(xpath = "(//*[text()='Add Employee'])[1]")
    public WebElement addEmpOption;

    @FindBy(xpath = "//*[@class='oxd-userdropdown-name']")
    public WebElement welcomeScreenLoc;

    @FindBy(xpath = "//*[@class='oxd-main-menu-item']")
    public WebElement adminPageOption;

    public DashboardPage(){

        PageFactory.initElements(driver, this);
    }
}
