Feature: Update employee contact information in system

  Background:
    Given user is able to access HRMS application
    When user enters valid username and password
    And user clicks on login button
    Then user is able to login successfully
    Then user clicks on my info tab
    And clicks on contact details tab

  @updateContact
  Scenario:
    Then user update address one
    And user updates address two
    And user updates city
    And user updates state
    And user updates zip code
    And user selects country
    Then user updates home phone
    And user updates mobile phone
    And user updates work phone
    Then user updates work email
    And user updates other email
    Then user clicks save button
