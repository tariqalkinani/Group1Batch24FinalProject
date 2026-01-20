package Steps;

import Pages.LoginPage;
import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginErrorValidationSteps extends CommonMethods {

    @When("user enters valid username and invalid password")
    public void user_enters_valid_username_and_invalid_password() {

        sendText(ConfigReader.read("userName"), loginPage.usernameField);
        sendText("wrongpass", loginPage.passwordField);
    }

    @Then("user system shows invalid credentials")
    public void user_system_shows_invalid_credentials() throws InterruptedException {

        WebElement invalidcred = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']"));
        Thread.sleep(3000);
        String text = invalidcred.getText();
        System.out.println(text);

    }

    @When("user enter valid username and empty password")
    public void user_enter_valid_username_and_empty_password() {

        sendText(ConfigReader.read("userName"), loginPage.usernameField);
    }

    @Then("user system shows required message")
    public void user_system_shows_required_message() throws InterruptedException {

        WebElement reqMess = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"));
        Thread.sleep(3000);
        String text=reqMess.getText();
        System.out.println(text);

    }
    @When("user enter empty username and valid password")
    public void user_enter_empty_username_and_valid_password() {

        sendText(ConfigReader.read("password"), loginPage.passwordField);
    }
    @Then("user system shows username required message")
    public void user_system_shows_username_required_message() {

        WebElement userReqmess = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"));
        String text=userReqmess.getText();
        System.out.println(text);


    }
    @Then("user system shows required message for username and password")
    public void user_system_shows_required_message_for_username_and_password() throws InterruptedException {

        WebElement userReqmess = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"));
        String text=userReqmess.getText();
        System.out.println(text);

        WebElement passreqMess = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"));
        Thread.sleep(3000);
        String text1=passreqMess.getText();
        System.out.println(text1);

    }
    @Then("user enters valid username and valid password")
    public void user_enters_valid_username_and_valid_password() throws InterruptedException {
        Thread.sleep(4000);

        sendText(ConfigReader.read("userName"), loginPage.usernameField);
        sendText(ConfigReader.read("password"), loginPage.passwordField);
    }




}
