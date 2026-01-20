package Steps;

import Pages.AddEmployeePage;
import Utils.CommonMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on Add Employee button")
    public void user_clicks_on_add_employee_button() {
        click(dashboardPage.addEmpOption);
    }

    @Given("admin add employee with firstname and lastname")
    public void admin_add_employee_with_firstname_and_lastname() {
        sendText("Smith", AddEmployeePage.firstName);
        sendText("Joe", AddEmployeePage.lastName);
    }

    @When("admin clicks on save button")
    public void admin_clicks_on_save_button() {
        click(AddEmployeePage.submitButton);
    }

    @Then("employee is added successfully")
    public void employee_is_added_successfully() {
        Assert.assertTrue(AddEmployeePage.employeeList.isDisplayed());
        System.out.println("employee is added successfully");
    }

    @Given("admin add employee with firstname and lastname and employeeID")
    public void admin_add_employee_with_firstname_and_lastname_and_employee_id() {
        sendText("Brown", AddEmployeePage.firstName);
        sendText("Irish", AddEmployeePage.lastName);
        sendText("0885", AddEmployeePage.employeeId);
    }

    @Given("admin add employee with firstname")
    public void admin_add_employee_with_firstname() {
        sendText("Garcia", AddEmployeePage.firstName);

    }

    @When("admin add employee with empty lastname")
    public void admin_add_employee_with_empty_lastname() {
        AddEmployeePage.lastName.clear();
    }

    @Then("displays error message")
    public void displays_error_message() {
        System.out.println(" error message");
    }

    @Given("admin add employee with lastname")
    public void admin_add_employee_with_lastname() {
        sendText("Wilson", AddEmployeePage.lastName);
    }

    @When("admin add employee with empty firstname")
    public void admin_add_employee_with_empty_firstname() {
        AddEmployeePage.firstName.clear();
    }

    @Given("admin add employee with empty firstname and lastname")
    public void adminAddEmployeeWithEmptyFirstnameAndLastname() {
        AddEmployeePage.firstName.clear();
        AddEmployeePage.lastName.clear();
    }

    @When("admin add employee with employeeID")
    public void adminAddEmployeeWithEmployeeID() {
        sendText("0089", AddEmployeePage.employeeId);
    }
}

