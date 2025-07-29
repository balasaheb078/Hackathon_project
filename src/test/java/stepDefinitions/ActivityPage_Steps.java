package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictActivitiesPage;
import com.test.resources.Activity;
import com.test.utilities.ExcelReaderWrite;
import com.test.utilities.ScreenShot;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActivityPage_Steps {
    
    WebDriver driver;
    DistrictActivitiesPage activities;
    List<Activity> activityInfo;
    
    @Given("the user navigates to the Activity page")
    public void open_the_activity_page() {
        driver = BaseClass.getDriver();
        activities = new DistrictActivitiesPage(driver);
        activities.clickActivitiesPage();
        // log after step completion
        BaseClass.getLogger().info("Step completed: Navigated to the Activity page");
    }
    
    @When("the system fetches all activities scheduled for the weekend and sorts the activities by ascending price")
    public void getting_all_the_activities_coming_in_weekends_and_sort_in_lowest_price_order() {
        activityInfo = activities.getActivites();
        // log after step completion
        BaseClass.getLogger().info("Step completed: Fetched and sorted weekend activities by ascending price");
    }
    
    @Then("display the sorted list of weekend activities to the user")
    public void print_all_the_activities_extracted() {
        activities.printActivites(activityInfo);
        BaseClass.setDriver(driver);
        ScreenShot.screenShotTC(BaseClass.getDriver(), "ActivitiesSorted");
        // log after step completion
        BaseClass.getLogger().info("Step completed: Displayed activities and captured screenshot");
    }
    
    @Then("export the activities to an Excel file")
    public void excelWriteOperation() throws IOException {
        String excelPath  = "src/test/resources/testdata/ExcelData.xlsx";
        String sheetName  = "SortedActivities";

        // write the Activity list into the sheet
        ExcelReaderWrite.writeActivities(excelPath, sheetName, activityInfo);
        System.out.println("✅ Activities written to Excel at " + excelPath);
        
        // log after step completion
        BaseClass.getLogger().info("Step completed: Exported activities to Excel sheet '" + sheetName + "' at " + excelPath);
    }
}
