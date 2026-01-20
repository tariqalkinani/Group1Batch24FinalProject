Feature: Edit Employee Details

Background:
   # Given user is able to access HRMS application
When user enters valid username and password
And user clicks on login button
Then user is able to login successfully
When user clicks on PIM option
And user clicks on employee list option

@Smoke @tariq1
Scenario: Edit employee by name
When When user enters valid Employee name "Scott" and "Hall"
And user clicks on search button
And user clicks on emp first name
And user selects gender Nationality and Marital status
Then user is able to see employee details and save changes
