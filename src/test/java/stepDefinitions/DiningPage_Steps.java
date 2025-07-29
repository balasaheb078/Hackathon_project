package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictDiningSearch;
import com.test.resources.Dining;
import com.test.utilities.ExcelReaderWrite;
import com.test.utilities.ScreenShot;

import io.cucumber.java.en.*;

public class DiningPage_Steps {
    private WebDriver driver;
    private DistrictDiningSearch diningPage;
    private List<Dining> diningInfo;

    @Given("the user is on the Dining Search page")
    public void openSearchPage() throws InterruptedException {
        driver = BaseClass.getDriver();
        driver.navigate().to("https://www.district.in/");
        BaseClass.getLogger().info("Step completed: Navigated to the Dining Search page");
    }

    @When("the user searches for {string}")
    public void searchCafe(String cafeName) throws InterruptedException {
        diningPage = new DistrictDiningSearch(driver);
        diningInfo = diningPage.getCafeInfo(cafeName);
        BaseClass.getLogger().info("Step completed: Searched for café '" + cafeName + "'");
    }

    @Then("display the café’s name, rating, price range, opening hours, and address in the console")
    public void printCafeInfo() {
        // All printing is already inside getCafeInfo()
        BaseClass.setDriver(driver);
        ScreenShot.screenShotTC(BaseClass.getDriver(), "DiningInfo");
        BaseClass.getLogger().info("Step completed: Displayed café information and captured screenshot");
    }

    @Then("write the café information to an Excel sheet")
    public void writeDataToExcel() throws IOException {
        String excelPath  = "src/test/resources/testdata/ExcelData.xlsx";
        String sheetName  = "Dining Details";

        // write the Dining Details into the sheet
        ExcelReaderWrite.writeDining(excelPath, sheetName, diningInfo);
        System.out.println("✅ Dining Details written to Excel at " + excelPath);
        BaseClass.getLogger().info("Step completed: Written café information to Excel sheet '" + sheetName + "'");
    }
}
