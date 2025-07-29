Feature: User Sign-In

  Scenario: Sign-In fails when phone number is missing
    Given the user opens the Sign-In page
    When the user clicks the submit button without entering a phone number
    Then the system displays an error message indicating that the phone number is required
