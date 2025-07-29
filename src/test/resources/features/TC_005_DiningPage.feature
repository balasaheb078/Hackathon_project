Feature: Dining Search and Café Details

  Scenario: Retrieve and Export Specific Café Information
    Given the user is on the Dining Search page
    When the user searches for "Kerala Cafe"
    Then display the café’s name, rating, price range, opening hours, and address in the console
    And write the café information to an Excel sheet
