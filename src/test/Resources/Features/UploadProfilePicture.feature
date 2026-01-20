Feature: Upload Profile Picture

  Background:
   # Given user is able to access HRMS application
    When user enters valid username and password
    And user clicks on login button
    Then user is able to login successfully
    When user clicks on PIM option
    And user clicks on employee list option

  @upload
  Scenario: Upload a Profile Picture
      #Given user uploads a profile picture to enhance personalization on the platform
    When user enters valid Employee name
    When user clicks on search button
    And user clicks on emp first name
    When user clicks on profile picture
    When user profile should display the updated profile picture
    Then user is able to see appropriate error messages for images that are not recommended dimensions

