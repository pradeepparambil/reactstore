# Reactstore UI Test Suite
AUT Code - https://github.com/pradeepparambil/react-shopping-cart
To start the app in local machine - npm start


## Features
* Optimized test suite - Possible end to end scenarios, avoid duplication, multiple test suites - full regression, golden test suite
* Test Organization - add, remove, enable disable, prioritize any tests seamlessly. Each test should be independent
* Parallel execution of tests - Increases speed
* CI builds - Jenkins
* Cross Browser Testing - Testing on multiple platforms, browsers and versions
* Testing locally(for development) and cloud machines(Grid) - Browserstack, Saucelab etc
* Execute tests again different environments (lower and upper) - Dev, UAT, Production 
* Parameterized tests
* Detailed test report - Summary, Status of each test, details of failure (expected, actual), screenshots for failures, video for the test sessions for cloud
* Detailed Logging for debugging (Logback, Slf4J)


## How to run allure report
allure serve /Users/pradeep/projects/qa/reactstore/allure-results

## Allure Annotations for tests
* @Description
* @Severity
* @Epic
* @Feature
* @Story
* @Step