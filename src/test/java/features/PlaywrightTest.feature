Feature: This feature file implements and tests integration of playwright and cucumber

  @CucumberTest
  Scenario Outline: Test playwright and cucumber integration
    Given User launches <app> with <url>
    When User clicks on login button on <app>
    Then User is logged in <app>

    Examples:
      | app      | url                      |
      | flipkart | https://www.flipkart.com |
      | amazon   | https://www.amazon.in    |