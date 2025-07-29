Feature: Events Listing and Price Sorting

  Scenario: List Pune events sorted by ascending price
    When the user navigates to the Events tab
    And the user scrolls until all events are loaded
    And the user fetches and sorts the events by ascending price
    Then print the sorted list of events
    And write the sorted events to an Excel sheet
