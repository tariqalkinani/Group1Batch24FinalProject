Feature: User Story to Admin user with valid login credentials

  @login
  Scenario: valid admin login with credentials
    Given admin is on login page
    When admin enters valid credentials with username and password
    And admin clicks on login button
    Then admin should be redirected to the dashboard page.