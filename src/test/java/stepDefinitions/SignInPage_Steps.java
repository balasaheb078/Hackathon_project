package stepDefinitions;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictSignInPage;
import com.test.utilities.ScreenShot;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignInPage_Steps {
    
    WebDriver driver;
    DistrictSignInPage signIn;
    
    @Given("the user opens the Sign-In page")
    public void open_user_page() {
        driver = BaseClass.getDriver();
        signIn = new DistrictSignInPage(driver);
        signIn.clickUserIcon();
        BaseClass.getLogger().info("Step completed: Opened Sign-In page and clicked user icon");
    }
    
    @When("the user clicks the submit button without entering a phone number")
    public void clicked_submit_without_filling_phone_number() {
        signIn.clickSubmitBtn();
        BaseClass.getLogger().info("Step completed: Clicked submit button without entering phone number");
    }
    
    @Then("the system displays an error message indicating that the phone number is required")
    public void print_the_error_message_shown() {
        ScreenShot.screenShotTC(BaseClass.getDriver(), "SignInPage");
        signIn.validateSignIn();
        BaseClass.getLogger().info("Step completed: Displayed error message for required phone number");
    }
}
