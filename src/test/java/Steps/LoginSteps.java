package Steps;

import Pages.DashboardPage;
import Pages.LoginPage;
import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginSteps extends CommonMethods {

    @Given("admin is on login page")
    public void admin_is_on_login_page() {
        openBrowserAndLaunchApplication();
    }

    @When("admin enters valid credentials with username and password")
    public void admin_enters_valid_credentials_with_username_and_password() {

        sendText(ConfigReader.read("userName"), loginPage.usernameField);
        sendText(ConfigReader.read("password"), loginPage.passwordField);
    }

    @When("admin clicks on login button")
    public void admin_clicks_on_login_button() {
        click(loginPage.loginButton);
    }

    @Then("admin should be redirected to the dashboard page.")
    public void admin_should_be_redirected_to_the_dashboard_page() {
        Assert.assertTrue(dashboardPage.welcomeScreenLoc.isDisplayed());
        System.out.println("test passed");
    }
}

