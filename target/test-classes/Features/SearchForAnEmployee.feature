Feature:Search for an Employee in the HRMS Application

  Background: Given user is able to access HRMS appication

  @Search
  Scenario:search for employees by their name or employee ID,
  So that I can quickly locate and access their records in the HRMS system
    Given user is an admin user
    Then user is able to search for employee using full name and the search should return all matching employee records
    And also the search functionality should be capable of handling partial name matches and variations in capitalization
    Then the admin user should be able search for an employee  using their unique employee id as the search criteria
    And the search should return the exact employee record associated with the provided employee ID
    And the system should provide a message "no records found" if no matching employee records are found
