# Git Automation Test

## Assumptions

- Java 17+ is installed
- Git CLI is installed and available in system PATH
- Git credentials (via HTTPS or SSH) are already set up locally (cached or configured)
- The repo has:
  - At least one existing file named 'README.md'
  - Write access granted to the user executing this script

## How It Works

- Uses `ProcessBuilder` to invoke CLI git commands
- Test 1 clones the repo and adds a new file with content
- Test 2 appends content to an existing file
- Uses TestNG as the test framework
- added all the test cases of a class to be run in testng.xml to execute in one go

## Run Instructions

- Run 'mvn clean test -DsuiteXmlFile=testng.xml' for executing all the test cases configured in testng.xml

## Allure Report

- Run 'mvn allure:report' command to generate the allure report after the test case results once testng.xml file is executed.
- This allure report will be generated tо directory: target/site/allure-maven/index.html


************************************************************************************************************************************************************************************


