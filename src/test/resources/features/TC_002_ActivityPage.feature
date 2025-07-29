Feature: Display and Export Weekend Activities

  Scenario: Retrieve and Export Weekend Activities
    Given the user navigates to the Activity page
    When the system fetches all activities scheduled for the weekend and sorts the activities by ascending price
    Then display the sorted list of weekend activities to the user
    Then export the activities to an Excel file
