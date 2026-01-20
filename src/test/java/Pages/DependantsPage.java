package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DependantsPage extends CommonMethods {

    @FindBy(xpath = "//a[text()='Dependents']")
    public WebElement dependantsOpt;

    @FindBy(xpath = "(//button[@class='oxd-button oxd-button--medium oxd-button--text'])[1]")
    public WebElement dependantsAddBtn;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    public WebElement dependantsName;

    @FindBy(xpath = "//input[@placeholder='yyyy-mm-dd']")
    public WebElement dependantsDOB;

    @FindBy(xpath = "//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")
    public WebElement dependantRelationsArrow;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    public WebElement dependantSaveBtn;

    @FindBy(xpath = "//h6[text()='Assigned Dependents']")
    public WebElement dependantWelcomePage;

    @FindBy(xpath = "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']")
    public WebElement recordFound;

    public String getDependentsHeaderText() {
        return dependantWelcomePage.getText();
    }

    @FindBy(xpath = "//div[@id='oxd-toaster_1']")
    public WebElement dependantAddedSuccessfully;

    @FindBy(xpath = "//div[@class='orangehrm-container']//tr")
    public List<WebElement>  dependantTable;

    @FindBy(xpath = "//div[@class='orangehrm-container']//div[@class='oxd-table-body']//tr")
    public WebElement dependantTableTwo;

    @FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'])[1]")
    public WebElement errorMsgEmptyName;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-form-hint']")
    public WebElement errorMsgDOB;

    @FindBy(xpath = "(//button[@class='oxd-icon-button oxd-table-cell-action-space'])[2]")
    public WebElement dependantEditBtn;

    @FindBy(xpath = "(//button[@class='oxd-icon-button oxd-table-cell-action-space'])[1]")
    public WebElement dependantRemoveBtn;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")
    public WebElement confirmRemoveBtn;

    @FindBy(xpath = "//DIV[@class='oxd-select-text oxd-select-text--active']")
    public WebElement dependantRelationsContainer;

    @FindBy(xpath = "(//div[@class='oxd-table-row oxd-table-row--with-border'])[2]")
    public WebElement firstDependantRow;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[3]")
    public WebElement specifyOtherField;

    public boolean isAddButtonVisibleAndEnabled() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(dependantsAddBtn));
        return dependantsAddBtn.isDisplayed() && dependantsAddBtn.isEnabled();

    }
    public boolean isNameTextboxDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(dependantsName));
        return dependantsName.isDisplayed();
    }

    public List<String> getRelationshipDropdownOptions() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(dependantRelationsArrow));

        Select select = new Select(dependantRelationsArrow);
        List<WebElement> options = select.getOptions();

        List<String> optionTexts = new ArrayList<>();
        for (WebElement option : options) {
            optionTexts.add(option.getText().trim());
        }

        return optionTexts;
    }
    public DependantsPage(){

        PageFactory.initElements(driver, this);
    }


}



