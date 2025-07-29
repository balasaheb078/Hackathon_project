Feature: Movie Seat Selection Flow

  Scenario: Select and verify seats using configuration data
    Given the user is on the Home page
    When the user clicks on the Movies menu
    And the user selects the movie from the property file
    And the user selects the date from the property file
    And the user selects the time slot from the property file
    And the user selects the number of seats defined in the property file
    Then the user should see the same number of seats selected as defined in the property file
