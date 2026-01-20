Feature: user story Login related scenario

  @Login
  Scenario: Valid admin login
    Given user is able to access HRMS application
    When user enters valid username and password
    And user clicks on login button
    Then user is able to login successfully
