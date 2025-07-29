package com.test.objectRepository;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import com.test.factory.BaseClass;
import com.test.utilities.ScreenShot;

public class DistrictSocialLinks {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    
    //Constructor
    public DistrictSocialLinks(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    //Locator
    @FindBy(xpath = "//a[text()='Events']")
    private WebElement eventsTab;

    @FindBy(id = "footer")
    private WebElement footer;

    @FindBy(xpath = "//*[@id='footer']//a[contains(@href,'facebook')]")
    private WebElement facebookIcon;

    @FindBy(xpath = "//*[@id='footer']//a[contains(@href,'instagram')]")
    private WebElement instagramIcon;

    @FindBy(xpath = "//*[@id='footer']//a[contains(@href,'youtube')]")
    private WebElement youtubeIcon;

    //Actions
    public void open(String url) {
    	//Opens the url given
        driver.get(url);
    }

    public void goToEvents() {
    	//Goes to events page
        wait.until(ExpectedConditions.elementToBeClickable(eventsTab)).click();
    }

    public void scrollToFooter() {
    	//Scrolls till footer
        js.executeScript("arguments[0].scrollIntoView(true);", footer);
    }

    public void clickAndVerifySocialIcon(String iconName, String expectedTitle) {
    	//Clicks the social media links in the footer
    	 WebElement icon;
    	    switch (iconName.toLowerCase()) {
    	        case "facebook":
    	            icon = facebookIcon;
    	            break;
    	        case "instagram":
    	            icon = instagramIcon;
    	            break;
    	        case "youtube":
    	            icon = youtubeIcon;
    	            break;
    	        default:
    	            throw new IllegalArgumentException("Unknown icon: " + iconName);
    	    }

    	    String mainHandle = driver.getWindowHandle();
    	    wait.until(ExpectedConditions.elementToBeClickable(icon)).click();

    	    // wait for new window then switch
    	    wait.until(d -> d.getWindowHandles().size() > 1);
    	    for (String handle : driver.getWindowHandles()) {
    	        if (!handle.equals(mainHandle)) {
    	            driver.switchTo().window(handle);
    	            ScreenShot.screenShotTC(BaseClass.getDriver(), "SocialLinks");
    	            break;
    	        }
    	    }

    	    // verify title and close
    	    wait.until(ExpectedConditions.titleIs(expectedTitle));
    	    driver.close();
    	    driver.switchTo().window(mainHandle);
    }
}
