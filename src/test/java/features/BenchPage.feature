Feature: Verify the Bench functionality

  Background:

  Background: Verify user is on login page
    Given the user is on the login page
    When user login into the application with email as "abhinandan.maholkar@coditas.com" and password as "ndbxf5pqax"
    Then verify that user should be redirect to Dashboard page with url as "https://codilytics.qa.coditas.org/dashboard"
    When user tap on bench tab from side bar

  @BenchTab
  Scenario: Verify the user is able to tap on bench tab
    Then User should be navigated to bench page as "https://codilytics.dev.coditas.org/bench"

  @benchCard
  Scenario: verify the bench cards is displayed
    Then verify that bench details cards should be displayed
      | Total Bench  |
      | Usable Bench |
      | Reserved     |
      | Trainees     |

  @benchFilters
  Scenario: Verify that bench filter functionality
    When user tap on the bench filter bar
    Then verify that Time month option is selected
    And verify that filter options
      | This Month          |
      | This Financial Year |
      | Custom Range        |


