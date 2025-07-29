package stepDefinitions;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictMovieSeat;
import com.test.utilities.PropertyUtility;
import com.test.utilities.ScreenShot;

import io.cucumber.java.en.*;

import static org.testng.Assert.*;

public class MovieSeat_Steps {
    private WebDriver driver;
    private DistrictMovieSeat moviePage;
    private Properties props;
    private int expectedSeats;

    @Given("the user is on the Home page")
    public void user_on_home_page() throws IOException {
        driver = BaseClass.getDriver();
        props = PropertyUtility.getMovieSeatProperties();
        driver.navigate().to("https://www.district.in/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		moviePage = new DistrictMovieSeat(driver,wait );
        BaseClass.getLogger().info("Step completed: Initialized Home page and MovieSeat page object");
    }

    @When("the user clicks on the Movies menu")
    public void user_clicks_movies_menu() {
        moviePage.openMoviesSection();
        BaseClass.getLogger().info("Step completed: Clicked on the Movies menu");
    }

    @When("the user selects the movie from the property file")
    public void user_selects_movie_from_property_file() {
        String movie = props.getProperty("movie");
        moviePage.selectMovie(movie);
        BaseClass.getLogger().info("Step completed: Selected movie '" + movie + "' from properties");
    }

    @When("the user selects the date from the property file")
    public void user_selects_date_from_property_file() {
        String date = props.getProperty("date");
        moviePage.selectDate(date);
        BaseClass.getLogger().info("Step completed: Selected date '" + date + "' from properties");
    }

    @When("the user selects the time slot from the property file")
    public void user_selects_time_slot_from_property_file() {
        String time = props.getProperty("time");
        moviePage.selectTimeSlot(time);
        BaseClass.getLogger().info("Step completed: Selected time slot '" + time + "' from properties");
    }

    @When("the user selects the number of seats defined in the property file")
    public void user_selects_number_of_seats_from_property_file() {
        expectedSeats = Integer.parseInt(props.getProperty("seatCount"));
        boolean success = moviePage.selectRandomSeats(expectedSeats);
        assertTrue(success, "Failed to select the expected number of seats");
        BaseClass.getLogger().info("Step completed: Selected " + expectedSeats + " seats as defined in properties");
    }

    @Then("the user should see the same number of seats selected as defined in the property file")
    public void user_should_see_same_number_of_seats() {
        int actualSeats = moviePage.getSelectedSeatsCount();
        assertEquals(actualSeats, expectedSeats,
            "The number of selected seats does not match the expected value");
        BaseClass.setDriver(driver);
        ScreenShot.screenShotTC(BaseClass.getDriver(), "MovieSeats");
        BaseClass.getLogger().info("Step completed: Verified selected seats count (" 
            + actualSeats + ") matches expected (" + expectedSeats + ") and captured screenshot");
    }
}
