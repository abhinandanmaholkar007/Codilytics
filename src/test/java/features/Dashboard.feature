Feature: Dashboard page

  Background:
    Given the user is on the login page

    Scenario: Verify the login page
      Then check Codilytics label font label as "MontSerrat-Regular", font size as "32px", color as "rgba(33, 150, 243, 1)" with the actual data
      And click on the Terms and condition link & verify the url
      And click on the privacy link & verify the url
#      And click on contac