Feature:add new employees to the HRMS system

  Background:
    Given admin is on login page
    When admin enters valid credentials with username and password
    And admin clicks on login button
    Then admin should be redirected to the dashboard page.
    When user clicks on PIM option
    And user clicks on Add Employee button


  @sprint1@examples
  Scenario: add employee without providing employee ID
    Given admin add employee with firstname and lastname
    When admin clicks on save button
    Then employee is added successfully

  @sprint2@examples
  Scenario: add employee with providing employeeID
    Given admin add employee with firstname and lastname and employeeID
    When admin clicks on save button
    Then employee is added successfully

  @sprint3@examples
  Scenario: add employee without providing lastname
    Given admin add employee with firstname
    When admin add employee with empty lastname
    And admin clicks on save button
    Then displays error message

  @sprint4@examples
  Scenario: add employee without providing firstname
    Given admin add employee with lastname
    When admin add employee with empty firstname
    And admin clicks on save button
    Then displays error message

  @sprint5@examples
  Scenario: add employee without providing firstname and lastname
    Given admin add employee with empty firstname and lastname
    When admin add employee with employeeID
    And admin clicks on save button
    Then displays error message


