Feature: Alphabetical City Search

  Scenario: User searches by letter "A" and exports the city results
    Given the user is on the homepage
    When the user waits until the homepage fully loads
    And the user clicks the menu button
    And the user hovers over the search section in the menu
    And the user scrolls to the alphabetical search area
    And the user clicks on the letter "A"
    Then the results for letter "A" should be displayed
    And the user prints all result buttons to the console
    And the user writes the city names to an Excel sheet
