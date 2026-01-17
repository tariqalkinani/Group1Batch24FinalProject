package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ValidateCredentialsPage extends CommonMethods {

    @FindBy(xpath = "(//*[@class='oxd-table-cell oxd-padding-cell'])[2]")
    public WebElement idField;

    @FindBy(xpath = "(//*[@class='oxd-table-cell oxd-padding-cell'])[3]")
    public WebElement firstAndMiddleField;

    @FindBy(xpath = "(//*[@class='oxd-table-cell oxd-padding-cell'])[4]")
    public WebElement lastField;

    @FindBy(xpath = "//*[text()='No Records Found']")
    public WebElement noResultsFound;

    public ValidateCredentialsPage() {

        PageFactory.initElements(driver, this);
    }
}
