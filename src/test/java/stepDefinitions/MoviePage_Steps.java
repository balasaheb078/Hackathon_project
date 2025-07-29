package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictMoviePage;
import com.test.resources.Movie;
import com.test.utilities.ExcelReaderWrite;
import com.test.utilities.ScreenShot;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MoviePage_Steps {
    
    WebDriver driver;
    DistrictMoviePage movie;
    List<Movie> movieList;
    
    @Given("the user opens the Movies page")
    public void open_the_movies_page() {
        driver = BaseClass.getDriver();
        movie = new DistrictMoviePage(driver);
        movie.clickMoviesPage();
        BaseClass.getLogger().info("Step completed: Opened the Movies page");
    }
    
    @When("the system retrieves the languages for all available movies")
    public void getting_all_the_languages_of_movies_present() throws Exception {
        movieList = movie.getMovieLanguage();
        BaseClass.getLogger().info("Step completed: Retrieved languages for all available movies");
    }
    
    @Then("print the list of identified languages")
    public void print_all_the_languages_extracted() {
        movie.printMovieLanguage(movieList);
        BaseClass.setDriver(driver);
        ScreenShot.screenShotTC(BaseClass.getDriver(), "MoviePage");
        BaseClass.getLogger().info("Step completed: Printed the list of identified languages and captured screenshot");
    }
    
    @Then("write the complete language list to an Excel file")
    public void writeListToExcel() throws IOException {
        String excelPath  = "src/test/resources/testdata/ExcelData.xlsx";
        String sheetName  = "Movies";

        // write the language list into the sheet
        ExcelReaderWrite.writeMovies(excelPath, sheetName, movieList);
        System.out.println("✅ Movies written to Excel at " + excelPath);
        BaseClass.getLogger().info("Step completed: Written complete language list to Excel sheet '" + sheetName + "' at " + excelPath);
    }
}
