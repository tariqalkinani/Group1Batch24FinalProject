package Steps;

import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.PimPage;
import Utils.CommonMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchForAnEmployee extends CommonMethods {

    @Given("user is an admin user")
    public void user_is_an_admin_user() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.syntaxhrm.com/web/index.php/auth/login");
        loginPage = new LoginPage();
        sendText("hrm_user", loginPage.usernameField);
        sendText("Hrm_user@123", loginPage.passwordField);
        click(loginPage.loginButton);
        dashboardPage = new DashboardPage();
        click(dashboardPage.pimOption);

    }
    @Then("user is able to search for employee using full name and the search should return all matching employee records")
    public void user_can_search_employee_by_full_name_and_verify_results() {
        PimPage pimPage=new PimPage();
        click(PimPage.EmployeeNameBox);
        sendText("Kevin Smith Martiness",PimPage.EmployeeNameBox);
        click(PimPage.clickKevin);
        click(PimPage.searchButton);
        click(dashboardPage.pimOption);
    }

    @Then("also the search functionality should be capable of handling partial name matches and variations in capitalization")
    public void also_the_search_functionality_should_be_capable_of_handling_partial_name_matches_and_variations_in_capitalization() {
        sendText("jOh",PimPage.SupervisorBox);
        click(PimPage.clickJohnDoe);
        click(PimPage.searchButton);
        click(dashboardPage.pimOption);


    }
    @Then("the admin user should be able search for an employee  using their unique employee id as the search criteria")
    public void the_admin_user_should_be_able_search_for_an_employee_using_their_unique_employee_id_as_the_search_criteria() throws InterruptedException {
        sendText("EMP876", PimPage.employeeIDbox);
        click(PimPage.searchButton);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOf(PimPage.employeeInfoBoxToClick));

    }
    @Then("the search should return the exact employee record associated with the provided employee ID")
    public void the_search_should_return_the_exact_employee_record_associated_with_the_provided_employee_id() {
        String expectedEmployeeId = "EMP876";
        By employeeDataRows = By.xpath("//div[contains(@class,'oxd-table-row') and contains(@class,'oxd-table-row--clickable')]");
        By noRecordsMsg = By.xpath("//span[text()='No Records Found']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for either data rows or "No Records Found"
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(employeeDataRows),
                ExpectedConditions.presenceOfElementLocated(noRecordsMsg)
        ));

        List<WebElement> rows = driver.findElements(employeeDataRows);

        if (rows.isEmpty()) {
            String actualMessage = driver.findElement(noRecordsMsg).getText();
            throw new AssertionError("No employee records found. Message: " + actualMessage);
        }

        // Retry mechanism for stale elements
        boolean found = false;
        int attempts = 0;
        while (attempts < 3 && !found) {
            try {
                rows = driver.findElements(employeeDataRows);
                for (WebElement row : rows) {
                    String rowText = row.getText().trim();
                    if (rowText.contains(expectedEmployeeId)) {
                        System.out.println("Employee found: " + rowText);
                        found = true;
                        break;
                    }
                }
            } catch (StaleElementReferenceException e) {
                attempts++;
                System.out.println("StaleElementReferenceException caught, retrying... attempt " + attempts);
            }
        }

        if (!found) {
            throw new AssertionError("Employee ID '" + expectedEmployeeId + "' not found in any row.");
        }
    }
    @Then("the system should provide a message {string} if no matching employee records are found")
    public void the_system_should_provide_a_message_if_no_matching_employee_records_are_found(String expectedMessage) {
        By employeeDataRows = By.xpath("//div[contains(@class,'oxd-table-row') and contains(@class,'oxd-table-row--clickable')]");
        By noRecordsMsg = By.xpath("//span[text()='No Records Found']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(employeeDataRows),
                ExpectedConditions.presenceOfElementLocated(noRecordsMsg)
        ));

        List<WebElement> rows = driver.findElements(employeeDataRows);

        if (rows.isEmpty()) {
            String actualMessage = driver.findElement(noRecordsMsg).getText().trim();
            Assert.assertEquals(actualMessage, expectedMessage,
                    "Expected message does not match actual message");
            System.out.println("Correct 'no records found' message displayed: " + actualMessage);
        } else {
            System.out.println("Records found in the table: " + rows.size() + ". Skipping 'no records' assertion.");
        }
    }

}
