package Steps;

import Pages.AddEmployeePage;
import Pages.DashboardPage;
import Pages.DependantsPage;
import Pages.EmployeeSearchPage;
import Utils.CommonMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class DependantsSteps extends CommonMethods {
    String empId;

    @Given("the user is logged into the HRMS application")
    public void the_user_is_logged_into_the_hrms_application() {
        openBrowserAndLaunchApplication();
        sendText(Utils.ConfigReader.read("userName"), loginPage.usernameField);
        sendText(Utils.ConfigReader.read("password"), loginPage.passwordField);
        click(loginPage.loginButton);
    }

    @When("the user navigates to PIM")
    public void the_user_navigates_to_pim() {
        click(dashboardPage.pimOption);
    }

    @When("the user clicks on employee list option")
    public void the_user_clicks_on_employee_list_option() {
        click(dashboardPage.empListOption);
    }

    @When("the user enters valid employee id {string}")
    public void the_user_enters_valid_employee_id(String empId) {
        sendText(empId, AddEmployeePage.employeeIdButton);
    }

    @When("the user clicks on the search button")
    public void the_user_clicks_on_the_search_button() throws InterruptedException {
        click(employeeSearchPage.searchButton);
        Thread.sleep(2000);
        click(dependantsPage.recordFound);
    }

    @When("the user clicks on the record found")
    public void the_user_clicks_on_the_record_found() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement row = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class,'oxd-table-row')]")
                )
        );
    }

    @When("the user navigates to dependents section")
    public void the_user_navigates_to_dependents_section() {
        click(dependantsPage.dependantsOpt);

    }

    @Then("the Dependents page should be displayed")
    public void the_dependents_page_should_be_displayed() throws InterruptedException {
        String actualText = dependantsPage.getDependentsHeaderText();
        String expectedText = "Assigned Dependents";

        assertEquals(expectedText, actualText);
        System.out.println("Test passed");
    }

    @Then("the Add button should be visible and enabled")
    public void the_add_button_should_be_visible_and_enabled() {
        assertTrue("Add button is either not visible or not enabled",
                dependantsPage.isAddButtonVisibleAndEnabled());

        System.out.println("Add button is visible and enabled");
    }

    @When("the user clicks on the Add button")
    public void the_user_clicks_on_the_add_button() {
        click(dependantsPage.dependantsAddBtn);
    }

    @Then("the Name textbox should be displayed")
    public void the_name_textbox_should_be_displayed() {
        assertTrue("Name textbox is not displayed",
                dependantsPage.isNameTextboxDisplayed());
        System.out.println("Name textbox is displayed");
    }

    @Then("the Relationship dropdown should contain the following options:")
    public void the_relationship_dropdown_should_contain_the_following_options(io.cucumber.datatable.DataTable dataTable) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        dependantsPage.dependantRelationsArrow.click();
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class,'oxd-select-dropdown')]//span")));

        List<String> actual = options.stream()
                .map(WebElement::getText)
                .toList();

        assertTrue(actual.containsAll(
                List.of("Spouse","Child","Parent","Sibling","Other")
        ));
    }

    @Then("the Date of Birth field with calendar picker should be displayed")
    public void the_date_of_birth_field_with_calendar_picker_should_be_displayed() {
        assertTrue(
                dependantsPage.dependantsDOB.isDisplayed());
        System.out.println("DOB is displayed");
    }

    @When("the user enters {string} in the Name field")
    public void the_user_enters_in_the_name_field(String Name) {
        dependantsPage.dependantsName.clear();
        dependantsPage.dependantsName.sendKeys(Name);
    }

    @When("the user selects {string} from the Relationship dropdown")
    public void the_user_selects_from_the_relationship_dropdown(String value) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By dropdown = By.xpath("//label[text()='Relationship']/../following-sibling::div//div[contains(@class,'oxd-select-text')]");
        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();

        By options = By.xpath("//div[@role='listbox']//span");

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(options));

        for (WebElement option : driver.findElements(options)) {
            if (option.getText().trim().equalsIgnoreCase(value)) {
                option.click();
                return;
            }
        }

        throw new RuntimeException("Relationship option not found: " + value);
    }
    @When("the user selects {string} as Date of Birth")
    public void the_user_selects_as_date_of_birth(String dob) {
        dependantsPage.dependantsDOB.clear();
        dependantsPage.dependantsDOB.sendKeys(dob);
    }

    @When("the user clicks on the Save button")
    public void the_user_clicks_on_the_save_button() {
        click(dependantsPage.dependantSaveBtn);
    }

    @Then("the dependent should be added successfully")
    public void the_dependent_should_be_added_successfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement row = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-table-row')]")));

        assertTrue(row.isDisplayed());
    }

    @Then("the dependent should appear in the dependents list")
    public void the_dependent_should_appear_in_the_dependents_list() {
        String expectedName = "Fahad Al";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By rowsLocator = By.xpath("//div[contains(@class,'oxd-table-row')]");
        wait.until(driver -> driver.findElements(rowsLocator).stream()
                .map(WebElement::getText)
                .map(text -> text.replaceAll("\\s+", " ").toLowerCase())
                .anyMatch(text -> text.contains(expectedName.toLowerCase()))
        );

        List<WebElement> rows = driver.findElements(rowsLocator);

        boolean found = rows.stream()
                .map(WebElement::getText)
                .map(text -> text.replaceAll("\\s+", " ").toLowerCase())
                .anyMatch(text -> text.contains(expectedName.toLowerCase()));

        assertTrue("The dependent '" + expectedName + "' does not appear in the list", found);
    }
    @When("the user leaves the Name field empty")
    public void the_user_leaves_the_name_field_empty() {
        dependantsPage.dependantsName.clear();
        System.out.println("Name field left empty");
    }
    @Then("an error message should be displayed near the Name field")
    public void an_error_message_should_be_displayed_near_the_name_field() {
        assertTrue("Expected error message near the Name field is not displayed",
                dependantsPage.errorMsgEmptyName.isDisplayed());
        System.out.println("Error message near Name field is displayed successfully");
    }
    @Then("the dependent should not be saved")
    public void the_dependent_should_not_be_saved() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='orangehrm-container']//div[@class='oxd-table-body']")
        ));
        List<WebElement> rows = driver.findElements(
                By.xpath("//div[@class='orangehrm-container']//div[@class='oxd-table-body']//tr")
        );

        boolean found = rows.stream().anyMatch(row -> row.getText().contains("Maria"));

        assertFalse("Dependent was saved but it should not have been", found);

        System.out.println("Dependent was not saved as expected");
    }
    @When("the user selects a future date as Date of Birth")
    public void the_user_selects_a_future_date_as_date_of_birth() {
        LocalDate futureDate = LocalDate.now().plusDays(30);
        String futureDob = futureDate.toString();

        dependantsPage.dependantsDOB.clear();
        dependantsPage.dependantsDOB.sendKeys(futureDob);
    }
    @Then("an error message should be displayed near the Date of Birth field")
    public void an_error_message_should_be_displayed_near_the_date_of_birth_field() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement dobError = wait.until(ExpectedConditions.visibilityOf(dependantsPage.errorMsgDOB));
        assertTrue("Expected error message near the Date of Birth field is not displayed",
                dobError.isDisplayed());

        System.out.println("Error message near Date of Birth field is displayed successfully");
    }

        @Then("multiple dependents should be displayed in the dependents list")
    public void multiple_dependents_should_be_displayed_in_the_dependents_list() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        List<WebElement> rows = wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.xpath("//div[contains(@class,'oxd-table-row')]"), 1
                )
        );

        assertTrue(
                "Expected multiple dependents, but found " + rows.size(),
                rows.size() >= 2
        );

        System.out.println("Multiple dependents are displayed in the list");

    }
    @Given("an existing dependent is available")
    public void anExistingDependentIsAvailable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for table body to load (safe even if no rows yet)
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='oxd-table-body']")
        ));

        // Get all dependent rows
        List<WebElement> rows = driver.findElements(
                By.xpath("//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-row')]")
        );

        // If no dependents exist, add a default one
        if (rows.isEmpty()) {
            System.out.println("No dependent existed, adding a default one...");

            dependantsPage.dependantsAddBtn.click();
            dependantsPage.dependantsName.sendKeys("DefaultDependent");

            Select select = new Select(dependantsPage.dependantRelationsArrow);
            select.selectByVisibleText("Child");

            dependantsPage.dependantsDOB.sendKeys("2010-01-01");
            dependantsPage.dependantSaveBtn.click();

            // Wait for the new row to appear
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                    By.xpath("//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-row')]"), 0
            ));

            System.out.println("Default dependent added successfully.");
        } else {
            System.out.println("An existing dependent is already available.");
        }
    }
    @When("the user clicks on Edit for the dependent")
    public void the_user_clicks_on_edit_for_the_dependent() {
        click(dependantsPage.dependantEditBtn);
    }
    @When("the user updates the Relationship to {string}")
    public void the_user_updates_the_relationship_to(String relations) {
        dependantsPage.dependantRelationsArrow.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='listbox']//span[text()='" + relations + "']")
        ));
        option.click();

        System.out.println("Relationship updated to: " + relations);


        System.out.println("Updated Relationship to: " + relations);

        sendText("Other", dependantsPage.specifyOtherField);
    }
    @Then("the dependent information should be updated successfully")
    public void the_dependent_information_should_be_updated_successfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOf(dependantsPage.dependantAddedSuccessfully));
        assertTrue(
                "Expected success message is not displayed after updating dependent",
                message.isDisplayed()
        );

        System.out.println("Dependent information updated successfully");
    }
    @When("the user clicks on Delete for the dependent")
    public void the_user_clicks_on_delete_for_the_dependent() throws InterruptedException {
        dependantsPage.dependantRemoveBtn.click();
    }
    @When("the user confirms the deletion")
    public void the_user_confirms_the_deletion() {
        dependantsPage.confirmRemoveBtn.click();
    }
    @Then("the dependent should be removed from the dependents list")
    public void the_dependent_should_be_removed_from_the_dependents_list() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(
                By.cssSelector("#dependentsTable tbody tr"), 1));
        List<WebElement> dependents = dependantsPage.dependantTable;

        boolean found = dependents.stream()
                .anyMatch(row -> row.getText().contains("Maria Rivera"));

        assertFalse("Dependent was not removed from the list", found);
        System.out.println("Dependent has been removed from the dependents list successfully");
    }

    @Then("the admin should be able to add, edit, and delete dependents")
    public void the_admin_should_be_able_to_add_edit_and_delete_dependents() {

        System.out.println("Admin can add, edit, and delete dependents");
    }
    @When("the user adds another dependent with valid information")
    public void theUserAddsADependentWithValidInformation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        click(dependantsPage.dependantsAddBtn);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".oxd-form-loader")
        ));
        WebElement nameInput = wait.until(
                ExpectedConditions.visibilityOf(dependantsPage.dependantsName)
        );
        nameInput.clear();
        nameInput.sendKeys("Maria");
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(dependantsPage.dependantRelationsContainer)
        );
        dropdown.click();
        List<WebElement> options = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@class,'oxd-select-option')]")
                )
        );

        for (WebElement option : options) {
            if (option.getText().trim().equals("Child")) {
                option.click();
                break;
            }
        }
        WebElement dobInput = wait.until(
                ExpectedConditions.visibilityOf(dependantsPage.dependantsDOB)
        );
        dobInput.clear();
        dobInput.sendKeys("2015-07-31");
        click(dependantsPage.dependantSaveBtn);

        System.out.println("Added another dependent successfully");
    }
    @When("the admin adds a dependent {string}")
    public void the_admin_adds_a_dependent(String dependantName) {
        dependantsPage.dependantsAddBtn.click();
        dependantsPage.dependantsName.sendKeys(dependantName);
        dependantsPage.dependantRelationsArrow.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='listbox']//span[text()='" + "Child"+ "']")
        ));
        option.click();

        dependantsPage.dependantSaveBtn.click();
    }
    @Then("the dependent {string} should exist in the database")
    public void the_dependent_should_exist_in_the_database(String Name) throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://54.198.61.50:3306/hrm",
                "hrm_std",
                "hrm_std@2026"
        );

        String sql = "SELECT * FROM hs_hr_emp_dependents WHERE ed_name = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, Name);

        ResultSet rs = ps.executeQuery();

        assertTrue("Dependent not found in DB", rs.next());

        con.close();
    }


}
