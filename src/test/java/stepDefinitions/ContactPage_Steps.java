package stepDefinitions;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictContactPage;
import com.test.utilities.ReadXML;
import com.test.utilities.ScreenShot;

import io.cucumber.java.en.*;

public class ContactPage_Steps {

    WebDriver driver;
    DistrictContactPage contact;

    String name, email, phone, message;
    String[] formData;

    @Given("the user is on the homepage2")
    public void the_user_is_on_the_homepage2() {
        driver = BaseClass.getDriver();
        contact = new DistrictContactPage(driver);
        System.out.println("User is on homepage2");
        BaseClass.getLogger().info("Step completed: Navigated to the Home page");
    }

    @When("the user scrolls to the bottom of the page")
    public void the_user_scrolls_to_the_bottom_of_the_page() {
        contact.scrollToBottom();
        BaseClass.getLogger().info("Step completed: Scrolled to bottom of page");
    }

    @And("the user clicks on the Contact link")
    public void the_user_clicks_on_the_contact_link() {
        contact.clickContactLink();
        BaseClass.getLogger().info("Step completed: Clicked on Contact link");
    }

    @And("the user selects an option from the contact dropdown")
    public void the_user_selects_an_option_from_the_dropdown() {
        contact.clickChooseInput();
        contact.clickDropdownOption();
        BaseClass.getLogger().info("Step completed: Selected an option from the Contact dropdown");
    }
    
    @And("the user initializes the XMLParser using utility file {string}")
    public void initializeXML(String file) throws ParserConfigurationException {
        ReadXML xml = new ReadXML();
        if (file.equals("ContactValidForm")) {
            formData = xml.getContactValidData();
        } else if (file.equals("ContactInvalidForm")) {
            formData = xml.getContactInvalidData();
        }
        BaseClass.getLogger().info("Step completed: Initialized XML parser and extracted data from " + file);
    }

    @And("the user enters {string} into the Name field")
    public void the_user_enters_in_the_name_field(String name) {
        this.name = formData[0];
        BaseClass.getLogger().info("Step completed: Entered name into Name field");
    }

    @And("the user enters {string} into the Email field")
    public void the_user_enters_in_the_email_field(String email) {
        this.email = formData[1];
        BaseClass.getLogger().info("Step completed: Entered email into Email field");
    }

    @And("the user enters {string} into the Phone field")
    public void the_user_enters_in_the_phone_field(String phone) {
        this.phone = formData[2];
        BaseClass.getLogger().info("Step completed: Entered phone number into Phone field");
    }

    @And("the user enters {string} into the Message field")
    public void the_user_enters_in_the_message_field(String message) {
        this.message = formData[3];
        BaseClass.getLogger().info("Step completed: Entered message into Message field");
    }

    @And("the user clicks the Submit button")
    public void the_user_clicks_the_submit_button() {
        contact.fillForm(name, email, phone, message);
        BaseClass.getLogger().info("Step completed: Clicked the Submit button");
        ScreenShot.screenShotTC(BaseClass.getDriver(), "ContactForm");
    }

    @And("the form result is printed")
    public void the_form_should_be_submitted_successfully() {
    	String message;
    	if(contact.checkSubmitBtn()) {
    		message = contact.getFeedbackMsg();
    	}else {
    		message = "Form not submitted";
    	}
        System.out.println(message);
        contact.goToHomePage();
        BaseClass.setDriver(driver);
        
        BaseClass.getLogger().info("Step completed: Result: '" + message + "'");
    }

    @Then("the form should show validation errors")
    public void the_form_should_show_validation_errors() {
        System.out.println("Form submission failed due to validation errors.");
        BaseClass.getLogger().info("Step completed: Form displayed validation errors");
    }
}
