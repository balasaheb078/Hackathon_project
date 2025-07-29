Feature: Contact Form Submission

  Scenario Outline: Submit contact form with multiple data sets
    Given the user is on the homepage2
    When the user scrolls to the bottom of the page
    And the user clicks on the Contact link
    And the user selects an option from the contact dropdown
    And the user initializes the XMLParser using utility file "<file>"
    And the user enters "name" into the Name field
    And the user enters "email" into the Email field
    And the user enters "phone" into the Phone field
    And the user enters "message" into the Message field
    And the user clicks the Submit button
    Then the form result is printed

    Examples:
      | file               | 
      | ContactValidForm   |
      | ContactInvalidForm |
