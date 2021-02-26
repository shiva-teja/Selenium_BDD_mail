Feature: Gmail login

Scenario: Gmail Login Page Home Screen

Given User is on the Gmail Landing Page
When User login to the application with valid Username and Password
Then User validates that logged in sucessfully
And User validates the Compose button is displayed
And user enters the recepient mail address,Subject and body
When user uploads the file and clicks send
Then user navigates to the sent folder
And Verifies the the subject of the recently sent file