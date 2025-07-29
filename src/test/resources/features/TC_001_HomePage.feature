Feature: Location Selection on Home Page

  Scenario: Successfully Change User Location
    Given the user is on the district home page
    When the user clicks on the location button
    And selects Pune from the list of available locations
    Then the location displayed on the home page should be updated to Pune
