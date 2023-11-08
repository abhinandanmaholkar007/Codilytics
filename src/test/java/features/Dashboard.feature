Feature: Dashboard page

  Background:
    Given the user is on the login page

  Scenario: Verify Codilytics label on login page
    When user go to the login page codilytics label should be present and matched with actual text "Codilytics"
    Then check Codilytics label font label as "MontSerrat-Regular", font size as "32px", color as "rgba(33, 150, 243, 1)" with the actual design

  Scenario: Verify Welcome to our Codilytics Portal label on login page
    And extract the actual text "Welcome to our Codilytics Portal" with expected text
    Then check welcome text label's font label as "MontSerrat-Regular", font size as "24px", color as "rgba(28, 28, 28, 1)" with the actual design

  Scenario: Verify footer link
    Then click on the Terms and condition link & verify the url
    Then click on the privacy link & verify the url

  Scenario: Verify contact admin button
    Then click on contact admin button and navigate to coditas compose mail

  Scenario: Verify signIn functionality
    When user clicks on the sign in button
    Then user should be logged in with coditas mail id and dashboard page should be displayed