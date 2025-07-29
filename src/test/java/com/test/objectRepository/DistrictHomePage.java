package com.test.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DistrictHomePage {
	WebDriver driver;
	WebDriverWait wait;
	
	//Constructor
	public DistrictHomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	//Locator
	@FindBy(css = ".dds-w-8.dds-h-8.dds-flex.dds-items-center.dds-justify-center") WebElement locationBtn;
	@FindBy(xpath = "//*[@id='page-content']/div[3]/div/div/div/div/div[2]/div[1]/div/div[10]/img") WebElement puneBtn;
	
	
	//Actions
	public void clickLocation() {
		//Clicks the location Button
		wait.until(ExpectedConditions.elementToBeClickable(locationBtn)).click();
	}
	
	public void clickPune() {
		//Clicks Pune Location from the list
		wait.until(ExpectedConditions.elementToBeClickable(puneBtn)).click();
	}
	
}
