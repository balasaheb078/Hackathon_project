package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictLetterSearch;
import com.test.utilities.ExcelReaderWrite;
import com.test.utilities.ScreenShot;

import io.cucumber.java.en.*;

public class LetterSearch_Steps {

    WebDriver driver;
    DistrictLetterSearch letter;

    @Given("the user is on the homepage")
    public void the_user_is_on_the_homepage() {
        driver = BaseClass.getDriver();
        letter = new DistrictLetterSearch(driver);
        System.out.println("User is on homepage");
        BaseClass.getLogger().info("Step completed: User is on the homepage");
    }

    @When("the user waits until the homepage fully loads")
    public void the_user_waits_for_the_page_to_load() throws InterruptedException {
        Thread.sleep(3000); // You can replace this with a proper wait if needed
        BaseClass.getLogger().info("Step completed: Waited for the homepage to fully load");
    }

    @When("the user clicks the menu button")
    public void the_user_clicks_on_the_menu_button() throws InterruptedException {
        letter.clickMenuButton();
        BaseClass.getLogger().info("Step completed: Clicked the menu button");
    }

    @When("the user hovers over the search section in the menu")
    public void the_user_hovers_over_the_search_section() {
        letter.hoverAndScroll();
        BaseClass.getLogger().info("Step completed: Hovered over the search section in the menu");
    }

    @When("the user scrolls to the alphabetical search area")
    public void the_user_scrolls_to_the_letter_search_area() {
        letter.hoverAndScroll(); // Already includes scroll
        BaseClass.getLogger().info("Step completed: Scrolled to the alphabetical search area");
    }

    @When("the user clicks on the letter {string}")
    public void the_user_clicks_on_the_letter(String letterChar) {
        letter.clickLetter(letterChar);
        BaseClass.getLogger().info("Step completed: Clicked on letter '" + letterChar + "'");
    }

    @Then("the results for letter {string} should be displayed")
    public void the_user_should_see_the_results_for_letter(String letterChar) {
        System.out.println("Results for letter: " + letterChar);
        // You can add validation logic here if needed
        BaseClass.getLogger().info("Step completed: Displayed results for letter '" + letterChar + "'");
    }

    @Then("the user prints all result buttons to the console")
    public void the_user_prints_all_result_buttons() {
        letter.printAllResultButtons();
        BaseClass.setDriver(driver);
        ScreenShot.screenShotTC(BaseClass.getDriver(), "LettersSearch");
        BaseClass.getLogger().info("Step completed: Printed all result buttons and captured screenshot");
    }
    
    @Then("the user writes the city names to an Excel sheet")
    public void writeCityToExcel() throws IOException {
        List<String> letterCities = letter.getLetterCity();
        String excelPath  = "src/test/resources/testdata/ExcelData.xlsx";
        String sheetName  = "Cities";

        // write the list of cities into the sheet
        ExcelReaderWrite.writeLetterCity(excelPath, sheetName, letterCities);
        System.out.println("✅ Cities written to Excel at " + excelPath);
        BaseClass.getLogger().info("Step completed: Written city names to Excel sheet '" + sheetName + "' at " + excelPath);
    }
}
