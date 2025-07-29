// File: DistrictDiningSearch.java
package com.test.objectRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.resources.Dining;

/**
 * Encapsulates Dining search: open Dining, search cafe, read details.
 */
public class DistrictDiningSearch {
    private WebDriver driver;
    WebDriverWait wait;
    
    //Constructor
    public DistrictDiningSearch(WebDriver driver) {
        this.setDriver(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    //Locator
    @FindBy(xpath = "//a[text()='Dining']")
    private WebElement diningMenu;

    @FindBy(xpath = "//*[@id='page-content']/section/div/div[2]/img")
    private WebElement cityImage;

    @FindBy(xpath = "//*[@id=\"page-content\"]/div[4]/div/div/div/div/div[1]/div[1]/div/input")
    private WebElement searchInput;

    @FindBy(xpath = "//*[@id=\"page-content\"]/div[4]/div/div/div/div/div[2]/div/div/div/div[1]/a")
    private WebElement firstResult;

    @FindBy(xpath = "//*[@id=\"restaurant-info\"]/div[1]/section[1]/child::*")
    private List<WebElement> restaurantDetails;


    //Searches for cafeName and prints its name, rate, price, time, address.
    public List<Dining> getCafeInfo(String cafeName) throws InterruptedException {
    	
    	Thread.sleep(3000);
    	wait.until(ExpectedConditions.visibilityOf(diningMenu)).click();
        System.out.println("clicked Dining");
        Thread.sleep(3000);
        
        wait.until(ExpectedConditions.elementToBeClickable(cityImage)).click();
        Thread.sleep(3000);
        searchInput.sendKeys(cafeName);
        Thread.sleep(3000);

        firstResult.click();
        Thread.sleep(3000);

        //Splitting string
        String name    = restaurantDetails.get(0).getText();
        String raw     = restaurantDetails.get(1).getText();
        String[] parts = raw.split("\\s*\\|\\s*");
        String address = restaurantDetails.get(2).getText();

        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Name    : " + name);
        System.out.println("Rate    : " + parts[0]);
        System.out.println("Price   : " + parts[1]);
        System.out.println("Time    : " + parts[2].replace("\n", "").replace("\r", ""));
        System.out.println("Address : " + address);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        
        //Adding details to a list
        List<Dining> diningInfo = new ArrayList<>();
        diningInfo.add(new Dining(name, parts[0], parts[1], parts[2].replace("\n", "").replace("\r", ""), address));
        return diningInfo;
    }

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}
