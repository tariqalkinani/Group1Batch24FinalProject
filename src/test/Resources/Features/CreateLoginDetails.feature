Feature: Creating Login details for an Employee in the
  HRMS Application
  @Login
  Scenario: create login credentials for a new employee in the HRMS system,so that the employee can access the
  HRMS application with unique login details
    Given employee is already created in the system and user has access to user management
    Then click add user(required fields are marked with *)
    And user can select ESS from the "User role" dropdown
    Then user can search for employee name in employee name field (using 'Scott Hall')
    Then user can select 'enabled' option under status field
    Then user can enter username in username field
    Then user is able to create a password in password field
    And user is able to confirm said password in confirm password field
    Then user can be created and linked to the selected employee














