Feature: Creating Login details for an Employee in the
  HRMS Application

  Background:
    Given admin is on login page
    When admin enters valid credentials with username and password
    And admin clicks on login button
    Then admin should be redirected to the dashboard page.
    When user clicks on PIM option
    And user clicks on Add Employee button


  @Login
  Scenario: create login credentials for a new employee in the HRMS system,so that the employee can access the
  HRMS application with unique login details
    Given employee is already created in the system and user has access to user management
    And user can select ESS from the "User role" dropdown
    Then user can search for employee name in employee name field (using 'Scott Hall')
    Then user can select 'enabled' option under status field
    Then user can enter username in username field
    Then user is able to create a password in password field
    And user is able to confirm said password in confirm password field
    Then user can be created and linked to the selected employee
