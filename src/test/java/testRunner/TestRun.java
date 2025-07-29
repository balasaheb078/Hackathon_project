package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.test.utilities.AllureReportOpener;

@Test
@CucumberOptions(features= {
							  "./src/test/resources/features/",
//							  "./src/test/resources/features/TC_001_HomePage.feature",
//							  "./src/test/resources/features/TC_009_MovieSeat.feature",
						   },
				 glue={"stepDefinitions"},
				 plugin = {
					        "pretty",
					        "html:target/cucumber.html",
					        "json:target/allure-results/cucumber.json",
					        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
					        
					    })

@Listeners({
    com.test.utilities.CustomListener.class,
    com.test.utilities.RetryTransformer.class
})

public class TestRun extends AbstractTestNGCucumberTests{
	@AfterSuite
	public void afterSuite()
	{
		AllureReportOpener.openAllureReport();
	}
}