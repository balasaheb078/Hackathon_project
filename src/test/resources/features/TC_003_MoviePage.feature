Feature: Language Extraction from Movies Section

  Scenario: Identify and Export Available Movie Languages
    Given the user opens the Movies page
    When the system retrieves the languages for all available movies
    Then print the list of identified languages
    Then write the complete language list to an Excel file
