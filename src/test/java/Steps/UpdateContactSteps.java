package Steps;

import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UpdateContactSteps extends CommonMethods {

    @Given("user is able to access HRMS application")
    public void user_is_able_to_access_hrms_application() {

        openBrowserAndLaunchApplication();
    }

    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        sendText(ConfigReader.read("userName"), loginPage.usernameField);
        sendText(ConfigReader.read("password"), loginPage.passwordField);
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {

        click(loginPage.loginButton);
    }

    @Then("user is able to login successfully")
    public void user_is_able_to_login_successfully() {

        System.out.println("Login complete");
    }

    @Then("user clicks on my info tab")
    public void user_clicks_on_my_info_tab() {

        WebElement myInfo = driver.findElement(By.xpath("//span[text()='My Info']"));
        myInfo.click();
    }

    @Then("clicks on contact details tab")
    public void clicks_on_contact_details_tab() {

        WebElement conTbutton = driver.findElement(By.xpath("//a[text()='Contact Details']"));
        conTbutton.click();
    }
    @Then("user update address one")
    public void user_update_address_one() throws InterruptedException {

        Thread.sleep(5000);
        //  WebElement addyField1 = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
        //addyField1.sendKeys("357 Green St");
        sendText("357 Green st", updateContactPage.addy1Field);
    }
    @Then("user updates address two")
    public void user_updates_address_two() throws InterruptedException {
        Thread.sleep(3000);


        sendText("Apt 10A",updateContactPage.addy2Field);
    }
    @Then("user updates city")
    public void user_updates_city() throws InterruptedException {
        Thread.sleep(3000);


        sendText("Charlotte",updateContactPage.cityField);
    }
    @Then("user updates state")
    public void user_updates_state() throws InterruptedException {
        Thread.sleep(3000);


        sendText("North Carolina",updateContactPage.stateField);
    }
    @Then("user updates zip code")
    public void user_updates_zip_code() throws InterruptedException {
        Thread.sleep(3000);

        sendText("28202",updateContactPage.zipField);
    }

    @Then("user updates home phone")
    public void user_updates_home_phone() throws InterruptedException {
        Thread.sleep(3000);


        sendText("704-555-6666",updateContactPage.homePhone);
    }
    @Then("user updates mobile phone")
    public void user_updates_mobile_phone() throws InterruptedException {
        Thread.sleep(3000);


        sendText("704-777-8888",updateContactPage.mobilePhone);
    }
    @Then("user updates work phone")
    public void user_updates_work_phone() throws InterruptedException {
        Thread.sleep(3000);

        sendText("704-333-4444",updateContactPage.workPhone);
    }
    @Then("user updates work email")
    public void user_updates_work_email() throws InterruptedException {


        Thread.sleep(3000);
        sendText("Shall@job.com",updateContactPage.workEmail);
    }
    @Then("user updates other email")
    public void user_updates_other_email() throws InterruptedException {


        Thread.sleep(3000);
        sendText("ScottH@gmail.com",updateContactPage.otherEmail);
    }
    @Then("user clicks save button")
    public void user_clicks_save_button() {


        WebElement saveButton = driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']"));
        saveButton.click();
    }
    @Then("user selects country")
    public void user_selects_country() throws InterruptedException {


        WebElement dropDown = driver.findElement(By.xpath("//div[@class='oxd-select-text-input']"));
        dropDown.click();

        Thread.sleep(2000);

        WebElement usaOp = driver.findElement(By.xpath("//div[@role='listbox']//span[normalize-space()='United States']"));
        usaOp.click();








    }



}
