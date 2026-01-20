package Steps;

import Pages.AdminPage;
import Pages.DashboardPage;
import Pages.LoginPage;
import Utils.CommonMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class CreateLoginDetails extends CommonMethods {
    @Given("employee is already created in the system and user has access to user management")
    public void employee_is_already_created_in_the_system_and_user_has_access_to_user_management() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.syntaxhrm.com/web/index.php/auth/login");
        loginPage = new LoginPage();
        sendText("hrm_user", loginPage.usernameField);
        sendText("Hrm_user@123", loginPage.passwordField);
        click(loginPage.loginButton);
        dashboardPage = new DashboardPage();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        click(dashboardPage.adminPageOption);


    }

    @Then("click add user\\(required fields are marked with *)")
    public void click_add_user_required_fields_are_marked_with() {
        AdminPage adminPage = new AdminPage();
        click(AdminPage.addButton);

    }

    @Then("user can select ESS from the {string} dropdown")
    public void user_can_select_ess_from_the_dropdown(String string) {

        click(AdminPage.userRoleDropdown);
        click(AdminPage.ESSoption);

    }

    @Then("user can search for employee name in employee name field \\(using {string})")
    public void user_can_search_for_employee_name_in_employee_name_field_using(String string) {
        sendText("Scott", AdminPage.employeeNameSearchField);
        click(AdminPage.clickScottHall);

    }

    @Then("user can select {string} option under status field")
    public void user_can_select_option_under_status_field(String string) {
        click(AdminPage.statusDropdown);
        click(AdminPage.enabledOption);

    }

    @Then("user can enter username in username field")
    public void user_can_enter_username_in_username_field() {
        sendText("username123", AdminPage.userNameField);

    }

    @Then("user is able to create a password in password field")
    public void user_is_able_to_create_a_password_in_password_field() {
        sendText("Syntaxisthebest123!", AdminPage.passwordField);

    }

    @Then("user is able to confirm said password in confirm password field")
    public void user_is_able_to_confirm_said_password_in_confirm_password_field() {
        sendText("Syntaxisthebest123!", AdminPage.confirmPasswordField);

    }

    @Then("user can be created and linked to the selected employee")
    public void user_can_be_created_and_linked_to_the_selected_employee() {
        click(AdminPage.saveEmployeeInfoButton);

    }


}


