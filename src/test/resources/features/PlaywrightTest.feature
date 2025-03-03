Feature: This feature file implements and tests integration of playwright and cucumber

  Scenario Outline: Test playwright and cucumber integration
    Given User launches <app> with <url>
    When User clicks on login button
    Then User is logged in

    Examples:
      | app      | url                      |
      | flipkart | https://www.flipkart.com |
      | amazon   | https://www.amazon.com   |