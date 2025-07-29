package stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictLogInPage;
import com.test.utilities.PropertyUtility;
import com.test.utilities.ScreenShot;

public class LogIn_Steps {
	WebDriver driver;
	Properties prop;
	DistrictLogInPage loginAndLogout;
	@Given("the user is on the Sign-Up page")
	public void user_is_on_the_signup_page() throws IOException {
		driver = BaseClass.getDriver();
		driver.navigate().to("https://www.district.in/");
		loginAndLogout = new DistrictLogInPage(driver);
        BaseClass.getLogger().info("Step completed: Sign-up page opened");
	}

	@When("the user clicks the profile icon")
	public void user_clicks_on_the_user_profile_icon() {
		loginAndLogout.clickOnUser();
	}

	@When("the user enters the mobile number from the Excel sheet")
	public void user_enters_mobile_number_from_excel() throws Exception {
		Properties p = PropertyUtility.getLogInProperties();
		String number = p.getProperty("mobileNo");
		loginAndLogout.enterMobNo(number);
		BaseClass.getLogger().info("Step completed: Mobile no Entered");
	}

	@When("the user clicks Continue")
	public void user_clicks_on_continue() {
		loginAndLogout.clickOnContinue();
		BaseClass.getLogger().info("Clicked continue");
	}

	@When("the user enters the OTP manually")
	public void user_enters_otp_manually() {
		loginAndLogout.enterOtp();
		ScreenShot.screenShotTC(BaseClass.getDriver(), "loginPage");
		BaseClass.getLogger().info("OTP entered");
	}

	@When("the user clicks Login")
	public void user_clicks_on_login() {
		loginAndLogout.clickOnLogin();
		BaseClass.setDriver(driver);
		BaseClass.getLogger().info("Step completed: Login clicked");
	}


	@Then("user verifies login status")
	public void user_verifies_login_status() {
	    loginAndLogout = new DistrictLogInPage(driver);
	    if(!loginAndLogout.isUserLoggedIn()) {
	    	loginAndLogout.enterOtp();
	    	loginAndLogout.clickOnLogin();
			BaseClass.setDriver(driver);
	    }
	    if (!loginAndLogout.isUserLoggedIn()) {
	    	System.out.println("Incorrect OTP...");
	        throw new AssertionError("Login was not successful.");
	    }
	    if(loginAndLogout.isUserLoggedIn()) {
	    	System.out.println("Login Successfull!");
	    }
	    BaseClass.getLogger().info("Step completed: Verified login status");
	}
}

