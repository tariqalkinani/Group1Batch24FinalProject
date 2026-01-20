package Steps;

import Utils.CommonMethods;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.By.xpath;

public class UploadProfilePictureSteps extends CommonMethods {

    @When("user enters valid Employee name")
    public void user_enters_valid_employee_name() {
        sendText("Scott Hall",editEmployeePage.employeeName);
    }

    @When("user clicks on profile picture")
    public void user_clicks_on_file_attatchment() {
        click(uploadEmployeeImagePage.empimage);
        click(uploadEmployeeImagePage.empAdd);



    }
    @When("user profile should display the updated profile picture")
    public void user_profile_should_display_the_updated_profile_picture() throws InterruptedException {
        WebElement profilePic = driver.findElement(xpath("//input[@type='file']")); // replace with actual locator
        String path="C:\\Users\\tariq\\OneDrive\\Desktop\\Final Project//profile_test.png";
        profilePic.sendKeys(path);
        // String fullPath = "C:\\Users\\Nabil\\Desktop\\Final\\group1B24\\src\\test\\Resources\\test-images\\profile_test.png";  // ← ensure filename is here!

        System.out.println("Uploading from: " + profilePic);

        profilePic.sendKeys(path);

        // Optional: short wait for any preview
        Thread.sleep(1000);  // temporary

        // Click save (use jsClick if overlay)
        jsClick(editEmployeePage.savebutton);

        // Key: Refresh page to force UI update
        //
        // driver.navigate().refresh();

        // Wait for reload & spinner gone
        getwait().until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-loading-spinner, .oxd-form-loader")));

        // Verify image changed (add this for debug)
        WebElement profileImg = driver.findElement(By.xpath("//img[contains(@class, 'employee-image') or @alt='Profile Picture' or contains(@src, 'photo')]"));
        String newSrc = profileImg.getAttribute("src");
        System.out.println("Profile image src after save/refresh: " + newSrc);




    }
    @Then("user is able to see appropriate error messages for images that are not recommended dimensions")
    public void user_is_able_to_see_appropiate_error_messages_for_images_that_are_not_recomended_dimensions() {
        Assert.assertEquals("Accepts jpg, .png, .gif up to 1MB. Recommended dimensions: 200px X 200px",uploadEmployeeImagePage.errorMsg.getText());

    }


}
