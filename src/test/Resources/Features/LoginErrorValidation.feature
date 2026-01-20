Feature: Validate Login Error

  Background: Given user is able to access HRMS appication

  @validLogin @drea
  Scenario:
    Given user is able to access HRMS application
    When user enters valid username and password
    And user clicks on login button
    Then user is able to login successfully

  @wrongPass @drea
  Scenario:
    When user enters valid username and invalid password
    And user clicks on login button
    Then user system shows invalid credentials

  @emptyPass @drea
  Scenario:
    When user enter valid username and empty password
    And user clicks on login button
    Then user system shows required message

  @emptyUser @drea
  Scenario:
    When user enter empty username and valid password
    And  user clicks on login button
    Then user system shows username required message

  @emptyuserpass @drea
  Scenario:
    Given user clicks on login button
    Then user system shows required message for username and password

  @reenterCred @drea
  Scenario:
    When user enters valid username and invalid password
    And user clicks on login button
    Then user system shows invalid credentials
    Then user enters valid username and valid password
    And user clicks on login button
    Then user is able to login successfully







