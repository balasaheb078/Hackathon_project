package stepDefinitions;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictLogInPage;
 
import com.test.utilities.ScreenShot;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogOut_Steps {
	WebDriver driver;
	Properties prop;
	DistrictLogInPage loginAndLogout;

	@When("the user clicks on the profile icon")
	public void user_clicks_on_the_user_profile_icon_again() {
		driver = BaseClass.getDriver();
		loginAndLogout = new DistrictLogInPage(driver);
		if(loginAndLogout.isUserLoggedIn()) {
			loginAndLogout.clickOnUser();
		}
		else {
			throw new AssertionError("Logout failed: User is not logged in.");
		}
		ScreenShot.screenShotTC(BaseClass.getDriver(), "logoutPage");
		BaseClass.getLogger().info("Step completed: Opened profile icon");
	}
	

	@Then("the user clicks the Logout button")
	public void user_clicks_on_logout() {
		loginAndLogout = new DistrictLogInPage(driver);

		if (loginAndLogout.isLogoutbuttonPresent()) {
			loginAndLogout.clickOnLogout();
			BaseClass.setDriver(driver);
		} else {
			throw new AssertionError("Logout failed: User is not logged in.");
		}
		BaseClass.getLogger().info("Step completed: user logged out");
	}

}