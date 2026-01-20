package Steps;

import Utils.CommonMethods;
import Utils.DbReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EditEmployeeSteps extends CommonMethods {

    String employeeId;
    String expectedFN;
    String expectedLN;

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        click(dashboardPage.pimOption);

    }

    @When("user clicks on employee list option")
    public void user_clicks_on_employee_list_option() {

        click(dashboardPage.empListOption);
    }

    @When("When user enters valid Employee name {string} and {string}")
    public void when_user_enters_valid_employee_name_and(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        sendText(fullName, editEmployeePage.employeeName); // ✅ fixed
        expectedFN = firstName;
        expectedLN = lastName;
        employeeId = getwait().until(ExpectedConditions.visibilityOf(editEmployeePage.employeeId))
                .getAttribute("value");
    }

    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
        click(employeeSearchPage.searchButton);
        //gets the id of employee from edit employee page
        employeeId = getwait().until(ExpectedConditions.visibilityOf(editEmployeePage.employeeId)).getAttribute("value");
        //gets the id of employee from edit employee page
        //   employeeId = getwait().until(ExpectedConditions.visibilityOf(editEmployeePage.employeeId)).getAttribute("value");

    }

    @When("user clicks on emp first name")
    public void user_clicks_on_emp_first_name() {


        click(editEmployeePage.empName);

    }
    public void selectFromOrangeHrmDropdown(WebElement arrow, String visibleText) {

        // Click the arrow (not the input)
        jsClick(arrow);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait for dropdown listbox to appear
        WebElement listBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='listbox']")
        ));

        // Find option by text (span OR div, deeply nested safe)
        WebElement option = listBox.findElement(
                By.xpath(".//*[normalize-space()='" + visibleText + "']")
        );

        jsClick(option);
    }
    public void selectGender(String gender) {
        WebElement genderRadio = driver.findElement(
                By.xpath("//label[normalize-space()='" + gender + "']/span")
        );
        jsClick(genderRadio);
    }
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


    @When("user selects gender Nationality and Marital status")
    public void user_selects_gender_nationality_and_marital_status() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.oxd-form-loader")));

        // Gender
        selectGender("Male");

        // Nationality
        selectFromOrangeHrmDropdown(
                editEmployeePage.Nationalitydropdown,
                "American"
        );

        // Marital Status
        selectFromOrangeHrmDropdown(
                editEmployeePage.MaritalStatusdropdown,
                "Married"
        );
    }
    @Then("user is able to see employee details and save changes")
    public void user_is_able_to_see_employee_details_and_save_changes() {
        getwait().until(ExpectedConditions.elementToBeClickable(employeeSearchPage.searchButton)).click();
        if (employeeId == null || employeeId.isEmpty()) {
            throw new AssertionError("Employee ID is null or empty. Cannot fetch employee details.");
        }

        String query = "select emp_firstname, emp_lastname from hs_hr_employees where employee_id =" + employeeId;
        List<Map<String, String>> dataFromDb = DbReader.fetch(query);
        if (dataFromDb.isEmpty()) {
            throw new AssertionError("No employee found in DB with employee_id = " + employeeId);
        }
        String actualFN = dataFromDb.get(0).get("emp_firstname");
        String actualLN = dataFromDb.get(0).get("emp_lastname");
        Assert.assertEquals("First name does not match", expectedFN, actualFN);
        Assert.assertEquals("Last name does not match", expectedLN, actualLN);

        System.out.println("DB Data: " + dataFromDb);
    }
}




