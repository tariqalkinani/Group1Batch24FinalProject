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

    }

    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
        click(employeeSearchPage.searchButton);

    }

    @When("user clicks on emp first name")
    public void user_clicks_on_emp_first_name() {

        getwait().until(ExpectedConditions.elementToBeClickable(editEmployeePage.empName)).click();
        employeeId = getwait()
                .until(ExpectedConditions.visibilityOf(editEmployeePage.employeeId))
                .getAttribute("value")
                .trim();
        System.out.println("Captured Employee ID: [" + employeeId + "]");
    }

    @When("user selects gender Nationality and Marital status")
    public void user_selects_gender_nationality_and_marital_status() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.oxd-form-loader")));
        // Nationality
        WebElement nationalityDropdown = driver.findElement(By.xpath("(//div[@class='oxd-select-text-input'])[1]"));
        nationalityDropdown.click();
        Thread.sleep(3000);
        WebElement americanOp = driver.findElement(By.xpath("//div[@role='listbox']//span[normalize-space()='American']"));
        americanOp.click();

        // Marital Status
        WebElement maritalDropdown = driver.findElement(By.xpath("(//div[@class='oxd-select-text-input'])[2]"));
        maritalDropdown.click();
        WebElement marriedOp = driver.findElement(By.xpath("//div[@role='listbox']//span[normalize-space()='Married']"));
        marriedOp.click();
        Thread.sleep(3000);
        editEmployeePage.savebutton.click();

    }

    @Then("user is able to see employee details and save changes")
    public void user_is_able_to_see_employee_details_and_save_changes() {
        WebElement empIdField = getwait().until(driver -> {
            WebElement field = editEmployeePage.employeeId;
            String val = field.getAttribute("value");
            return (val != null && !val.isEmpty()) ? field : null;
        });

        // Capture employee ID
        employeeId = empIdField.getAttribute("value").trim();
        System.out.println("Captured Employee ID: " + employeeId);

        String query =
                "SELECT emp_firstname, emp_lastname " +
                        "FROM hs_hr_employee " +
                        "WHERE employee_id = " + Integer.parseInt(employeeId);

        List<Map<String, String>> dataFromDb = DbReader.fetch(query);

        Assert.assertFalse(
                "No employee found in DB with employee_id = " + employeeId,
                dataFromDb.isEmpty()
        );

        String actualFN = dataFromDb.get(0).get("emp_firstname");
        String actualLN = dataFromDb.get(0).get("emp_lastname");

        Assert.assertEquals("First name does not match", expectedFN, actualFN);
        Assert.assertEquals("Last name does not match", expectedLN, actualLN);

        System.out.println("Employee verified in DB: " + dataFromDb);
    }
}





