Feature: Add Dependents to Employee Profile
  As an employee or admin
  I want to add, edit, and remove dependents
  So that dependent information is accurately recorded and accessible


  Background:
    Given the user is logged into the HRMS application
    When the user navigates to PIM
    And the user clicks on employee list option
    And the user enters valid employee id "EMP0099"
    And the user clicks on the search button
    When the user navigates to dependents section
    And the user clicks on the record found
    Then the Dependents page should be displayed
    And the Add button should be visible and enabled

  @one @ui
  Scenario: Verify dependent form fields
    When the user clicks on the Add button
    Then the Name textbox should be displayed
    And the Relationship dropdown should contain the following options:
      | Spouse |
      | Child |
      | Parent |
      | Sibling |
      | Other |

    And the Date of Birth field with calendar picker should be displayed


  @one @regression @ui
  Scenario: Add a dependent with valid information
    When the user clicks on the Add button
    And the user enters "Fahad Al" in the Name field
    And the user selects "Child" from the Relationship dropdown
    And the user selects "2015-06-15" as Date of Birth
    And the user clicks on the Save button
    Then the dependent should be added successfully
    And the dependent should appear in the dependents list


  @one @negative @validation
  Scenario: Validate required Name field
    When the user clicks on the Add button
    And the user leaves the Name field empty
    And the user clicks on the Save button
    Then an error message should be displayed near the Name field
    And the dependent should not be saved


  @one @negative @validation
  Scenario: Validate invalid Date of Birth
    When the user clicks on the Add button
    And the user enters "Maria Rivera" in the Name field
    And the user selects "Child" from the Relationship dropdown
    And the user selects a future date as Date of Birth
    And the user clicks on the Save button
    Then an error message should be displayed near the Date of Birth field
    And the dependent should not be saved


  @one @regression
  Scenario: Add multiple dependents
    When the user adds another dependent with valid information
    Then multiple dependents should be displayed in the dependents list



  @one @regression
  Scenario: Edit an existing dependent
    Given an existing dependent is available
    When the user clicks on Edit for the dependent
    And the user updates the Relationship to "Other"
    And the user clicks on the Save button
    Then the dependent information should be updated successfully


  @one @regression
  Scenario: Delete an existing dependent
    Given an existing dependent is available
    When the user clicks on Delete for the dependent
    And the user confirms the deletion
    Then the dependent should be removed from the dependents list


  @one @db
  Scenario: Verify database entry after adding dependent
    When the admin adds a dependent "Layla Zimmerman"
    Then the dependent "Layla Zimmerman" should exist in the database
    Then the admin should be able to add, edit, and delete dependents

