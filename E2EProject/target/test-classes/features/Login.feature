 Feature: Login into the Application

Scenario Outline: Positive test scenario
	Given Initialize the browser with chrome in a maximum window size
	And Navigate to the "https://qaclickacademy.com/" website as a Home page
	And Define the explicit wait
	And Click on the "No thanks" button
	And Click on the Login link in order to get to the Login page
	When User logs in into the application with an <username> and <password>
	And Clicks on the Login button
	Then Verify that a user is successfully logged in
	And Close borwsers

	
	Examples:
	|username						|password			|
	|test99@gmail.com		|123456				|
	|test999@gmail.com	|12345				|