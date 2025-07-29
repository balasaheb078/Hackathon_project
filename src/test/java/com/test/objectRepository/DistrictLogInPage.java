package com.test.objectRepository;
 
import java.time.Duration;
import java.util.Scanner;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class DistrictLogInPage{
	WebDriver driver;
	
	//Constructor
	public DistrictLogInPage(WebDriver driver) {	
		this.driver = driver; 
		PageFactory.initElements(driver, this); // Re-initialization for local elements
	}
 
	//Locators
	@FindBy(xpath = "//*[@id=\"master-header\"]/div/div[1]/div[2]/div[2]/a")
	WebElement userProfile;

	@FindBy(xpath = "//*[@id='master-header']/div/div[1]/div[2]/div[2]/a/div[contains(text(),'U')]")
	WebElement LoggedInUser;

	@FindBy(xpath = "//*[@id=\"page-content\"]/div[3]/div/div/div/div/div[2]/div[2]/div[2]/div/input")
	WebElement mobNo;
 
	@FindBy(xpath = "//*[@id=\"page-content\"]/div[3]/div/div/div/div/div[2]/button")
	WebElement continueBtn;
 
	@FindBy(xpath = "//*[@id=\"page-content\"]/div[3]/div/div/div/div/div[2]/button")
	WebElement loginBtn;
 
	@FindBy(xpath = "//span[text()='Log Out']")
	WebElement logoutBtn;
	
	@FindBy(xpath = "//*[@id=\"page-content\"]/div[3]/div/div/div/div/div[2]/label")
	WebElement enterOtpText;
	
	//Actions
	public void clickOnUser() {
		// Clicks on user Profile
		userProfile.click();
	}
 
	public void clickOnContinue() {
		// Clicks the Continue button
		continueBtn.click();
	}
 
	public void clickOnLogin() {
		// Clicks on login
		loginBtn.click();
	}
 
	public void clickOnLogout() {
		// Clicks on logout
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
	}
	
	public boolean isLogoutbuttonPresent() {
		//Checks if Logout button is present
		try {
	        return logoutBtn.isDisplayed(); // If userloggedIn logo displayed, user is logged in
	    } catch (Exception e) {
	        return false; // Element not found, assume not logged in
	    }
	}
	
	public void enterMobNo(String Mob) {
		//Fills mobile number
		mobNo.sendKeys(Mob);
	}
	
	public boolean isLoginDisabled() {
		//Checks if login button is disabled
		if(loginBtn.isEnabled()) {
			return false;
		}
		return true;
	}

	public boolean isUserLoggedIn() {
		try {
			return LoggedInUser.isDisplayed(); //If userloggedIn logo displayed, user is logged in
		} catch (Exception e) {
		    return false; // Element not found, assume not logged in
		}
	}
	
	
	public void enterOtp() {
		//Asks input from user for OTP on console
		Scanner scanner = new Scanner(System.in);
		System.out.println("First Enter OTP then enter 'ok' to continue ");
		String code = "";
		int cnt = 0;
 
		while (!code.equalsIgnoreCase("ok") || cnt==0) {
			if (cnt > 0) {
				System.out.println("Enter 'ok' to continue");
			}
			code = scanner.nextLine();
			//Executes if otp code is 6 digit and between 0-9
			if (code.length() == 6 && code.matches(("\\d+")) && cnt == 0) {
				for (int i = 1; i <= 6; i++) {
					char digit = code.charAt(i - 1); // Get each digit
					String xpath = "//*[@id='page-content']/div[3]/div/div/div/div/div[2]/div[2]/div/input[" + i + "]";
					WebElement inputField = driver.findElement(By.xpath(xpath));
					inputField.clear();
					inputField.sendKeys(Character.toString(digit));
					cnt++;
				}
				System.out.println("Waiting... Type 'ok' to Continue with Automation.");
				code = scanner.nextLine();
			}
			else if (cnt == 0) {
				System.out.println("Invalid Input...Enter 6 Digits OTP Only");
			}
		}
	}
}