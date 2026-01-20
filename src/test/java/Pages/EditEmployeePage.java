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
    @FindBy(xpath ="//input[@class='oxd-input oxd-input--active']")
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

    public void selectDropdownOptionByText(WebElement dropdownContainer, String optionText) {
        // Click the container to open dropdown
        jsClick(dropdownContainer);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait for any dropdown to appear
        List<WebElement> options = wait.until(driver ->
                driver.findElements(By.xpath("//div[contains(@class,'oxd-select-dropdown')]//div"))
                        .stream()
                        .filter(WebElement::isDisplayed)
                        .collect(Collectors.toList())
        );

        // Click the option with matching text
        boolean clicked = false;
        for (WebElement option : options) {
            if (option.getText().trim().equals(optionText)) {
                jsClick(option);
                clicked = true;
                break;
            }
        }

        if (!clicked) {
            throw new RuntimeException("Option '" + optionText + "' not found in dropdown");
        }
    }

    // JS click helper
    public void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public List<String> getNationalityDropdownOptions() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(Nationalitydropdown));

        Select select = new Select(Nationalitydropdown);
        List<WebElement> options = select.getOptions();

        List<String> optionTexts = new ArrayList<>();
        for (WebElement option : options) {
            optionTexts.add(option.getText().trim());
        }

        return optionTexts;
    }

    public EditEmployeePage(){

        PageFactory.initElements(driver, this);
    }
}
