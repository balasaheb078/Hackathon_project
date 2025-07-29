package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictHomePage;
import com.test.utilities.ScreenShot;

public class HomePage_Steps {
    
    WebDriver driver;
    DistrictHomePage home;
    
    @Given("the user is on the district home page")
    public void user_is_on_the_district_home_page() {
        driver = BaseClass.getDriver();
        home = new DistrictHomePage(driver);
        BaseClass.getLogger().info("Step completed: User is on the district home page");
    }
    
    @When("the user clicks on the location button")
    public void user_clicks_the_location_button() {
        home.clickLocation();
        BaseClass.getLogger().info("Step completed: Clicked on the location button");
    }
    
    @When("selects Pune from the list of available locations")
    public void clicks_the_pune_location() {
        home.clickPune();
        BaseClass.getLogger().info("Step completed: Selected Pune from the list of available locations");
    }
    
    @Then("the location displayed on the home page should be updated to Pune")
    public void the_location_should_be_changed_to_pune() {
        System.out.println("Location changed to Pune");
        BaseClass.setDriver(driver);
        ScreenShot.screenShotTC(BaseClass.getDriver(), "HomePage");
        BaseClass.getLogger().info("Step completed: Location displayed on the home page is updated to Pune");
    }
}
