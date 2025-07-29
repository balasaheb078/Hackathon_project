package com.test.objectRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.resources.Activity;
import com.test.utilities.TimeParser;

public class DistrictActivitiesPage {
	WebDriver driver;
	WebDriverWait wait;
	int lastCount;
	
	//Constructor
	public DistrictActivitiesPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	//Locators
	@FindBy(css = "a.dds-h-full") List<WebElement> activities;
	@FindBy(xpath = "//a[text()='Activities']") WebElement activitiesBtn;
	
	//Actions
	public void clickActivitiesPage() {
		//Moves the Activities Tab
		wait.until(ExpectedConditions.elementToBeClickable(activitiesBtn)).click();
	}
	
	private int parsePrice(String txt) {
		//Parsing price from activities
        if (txt == null || txt.isEmpty()) return -1;
        txt = txt.toLowerCase();
        if (txt.contains("free")) return 0;
        txt = txt.replaceAll("[\\$,₹,]", "").replace("onwards", "").trim();
        try { return Integer.parseInt(txt); }
        catch (NumberFormatException e) { return -1; }
    }
	
	public List<Activity> getActivites() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		lastCount = activities.size();
		while (true) {
			try {
					// Scrolls to the bottom
				    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	
				    // Waits for new activities to load
				    boolean contentLoaded = new WebDriverWait(driver, Duration.ofSeconds(4))
				        .until(driver1 -> { 
				            List<WebElement> currentActivities = driver1.findElements(By.cssSelector("a.dds-h-full"));
				            return currentActivities.size() > lastCount;
				        });	
				    lastCount = driver.findElements(By.cssSelector("a.dds-h-full")).size();
			}catch(Exception e) {
				break;
			}
		}
		
		
		
		
		//Storing all activities in a list
		activities= driver.findElements(By.cssSelector("a.dds-h-full"));
		System.out.println(" collecting elements");
		
		List<Activity> activityInfo = new ArrayList<>();
		
		for (WebElement act :activities) {
			//Splitting String
			String text = act.getText();
			String[] parts = text.split("\\R");
			String time, activity, location, price;
			time = parts[0];
			if(time.contains(",")) {
				activity = parts[1];
				location = parts[2];
				price = "" + parsePrice(parts[parts.length - 1]);
			}else {
				time = parts[1];
				activity = parts[2];
				location = parts[3];
				price = "" + parsePrice(parts[parts.length - 1]);
			}
			
						
		    double doublePrice = Double.parseDouble(price); //Parse double digit
		    
		    //Splitting date and time
		    String[] timeParts = time.split(",");
		    String datePart = timeParts[0].trim();
		    String timePart = timeParts[1].trim();

		    LocalDateTime startTime, endTime = null;
		    int defaultYear = 2025;
		    
		    //Parsing date and time from the string
		    if (datePart.contains("-")) {
		        String[] dateRange = datePart.split("-");
		        startTime = TimeParser.parseDateTime(dateRange[0].trim(), timePart, defaultYear);
		        endTime = TimeParser.parseDateTime(dateRange[1].trim(), timePart, defaultYear);
		    } else {
		        startTime = TimeParser.parseDateTime(datePart.trim(), timePart, defaultYear);
		    }
		    
		    //Adding to the list
		    activityInfo.add(new Activity(startTime, endTime, activity, location, doublePrice));
		}
		
		//Filtering activities coming on weekends and sorted in lowest price
		List<Activity> weekendActivities = activityInfo.stream()
			    .filter(TimeParser::isHappeningOnWeekend)
			    .sorted(Comparator.comparingDouble(Activity::getPrice))
			    .collect(Collectors.toList());
		
		return weekendActivities;
	}
	
	public void printActivites(List<Activity> weekendActivities) {
		//Printing activities
		for (Activity act : weekendActivities) {
		    System.out.println(act);
		}
	}
}
