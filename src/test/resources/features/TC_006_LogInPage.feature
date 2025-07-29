Feature: Mobile Authentication Login Flow

  Scenario: Successful login with mobile number and OTP
    Given the user is on the Sign-Up page
    When the user clicks the profile icon
    And the user enters the mobile number from the Excel sheet
    And the user clicks Continue
    And the user enters the OTP manually
    And the user clicks Login
    Then user verifies login status
