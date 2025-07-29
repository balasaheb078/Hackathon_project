Feature: Footer Social Media Icons

  Scenario Outline: Each social icon opens the correct page
    Given the user navigates to the Home page
    When the user scrolls down to the footer
    And the user clicks the "<icon>" icon and verifies the page title is "<title>"
    Then the user closes the social media window

    Examples:
      | icon     | title                                      |
      | Facebook | District Updates \| Facebook               |
      | YouTube  | District Culture - YouTube                 |
      