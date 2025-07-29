package com.test.factory;
 
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
 
public class BaseClass {
 
	static WebDriver driver;
	static Properties p;
	static Logger logger;
 
	public static WebDriver initilizeBrowser(String browser) throws IOException {
		//Gets string from property file
		p = getProperties();
		String executionEnv = p.getProperty("executionEnvironment");
		//Grid Execution
		if (executionEnv.equalsIgnoreCase("remote")) {
			//URL for Grid execution
			URL url = URI.create("http://10.112.55.13:4444").toURL();
			if (browser.equalsIgnoreCase("chrome")) {
		        ChromeOptions options = new ChromeOptions();
		        driver = new RemoteWebDriver(url, options);
 
		    } else if (browser.equalsIgnoreCase("edge")) {
		        EdgeOptions options = new EdgeOptions(); 
		        driver = new RemoteWebDriver(url, options);
 
			} else {
				System.out.println("No browser matched for remote execution.");
				return null;
			}
 
		}
		//Local Execution
		if (executionEnv.equalsIgnoreCase("local")) {
			switch (browser) {
			case "chrome":
				ChromeOptions options = new ChromeOptions();
			    driver = new ChromeDriver(options);
				break;
			case "edge":
				EdgeOptions option = new EdgeOptions();
				driver = new EdgeDriver(option);
				break;
			default:
				System.out.println("Invalid browser name");
				return null;
			}
		}
		driver.manage().deleteAllCookies();
		//Page load timeout of 30 seconds
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
 
 
		return driver;
 
	}
 
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static void setDriver(WebDriver driver) {
		BaseClass.driver = driver;
	}
 
	public static Properties getProperties() throws IOException {
		//Gets property file from resources
		FileReader file = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		return p;
	}
 
	public static Logger getLogger() {
		//Returns Log4j logger
		logger = LogManager.getLogger(); // Log4j
		return logger;
	}
 
}