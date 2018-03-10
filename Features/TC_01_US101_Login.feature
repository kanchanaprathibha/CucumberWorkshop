# Author:Prathibha K
# User Story ID : US101
# Data Required : Yes
# Data Source : NA
# Date of Design : 17- Feb- 2018
# Reviewer : Prathap
# Comments : 

# Modified  Date:


Feature: Validate The E2E functionzlity of Login page

@Smoketest @Regression
Scenario: As per the user story US101(paste user story here), i need to validate the admin credentials with valid input data

Given Launch the firefox browser
And Enter URL
Then maximize the browser
When username is available enter the "valid" credentials

@Regression
Scenario: As per the user story US101(paste user story here), i need to validate the admin credentials with valid input data

Given Launch the firefox browser
And Enter URL
Then maximize the browser
When username is available enter the "invalid" credentials

 