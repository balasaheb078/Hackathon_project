package stepDefinitions;

import java.time.Duration;
import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

import com.test.factory.BaseClass;


public class Hooks {
	
	static WebDriver driver;
	
	@BeforeAll
    public static void setup() throws Exception
    {
		String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
	    driver = BaseClass.initilizeBrowser(browser);
	    driver.get("https://www.district.in/");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	    driver.manage().window().maximize();
	}
	
	@After
	public void checkScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
		}}
	
    @AfterAll
    public static void tearDown() {

       driver.quit();
    }
}
