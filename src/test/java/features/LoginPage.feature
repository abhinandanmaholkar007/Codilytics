Feature:Verify the Login functionality

  Background:
    Given the user is on the login page

  @verifyFontSizeColour
  Scenario Outline: Verify the font, colour and size
    Then verify that the <Element> font should be displayed as <SizeAndFont>
    And verify that the <Element> colour should be displayed as <Colour>

    Examples:
      | Element                   | SizeAndFont               | Colour               |
      | "Codilytics"              | "32px MontSerrat-Regular" | "rgb(33, 150, 243)"  |
      | "Codilytics Portal"       | "24px MontSerrat-Regular" | "rgb(28, 28, 28)"    |
      | "Coditas G-suite account" | "16px Roboto-Regular"     | "rgb(112, 112, 112)" |


  @verifyFooterLink
  Scenario: Verify footer link functionality
    Then verify that terms and condition link and Privacy policy should be displayed

  @contactAdmin
  Scenario: Verify the contact admin functionality
    Then verify that user should be able to tap on contact admin link
    And verify that user should redirect to Gmail login


  @validEmail
  Scenario: Case 1 Verify that the user is able to log in with coditas email id
    When user login into the application with email as "abhinandan.maholkar@coditas.com" and password as "ndbxf5pqax"
    Then verify that user should be redirect to Dashboard page with url as "https://codilytics.qa.coditas.org/dashboard"

  @invalidEmail
  Scenario: Case 2 Verify that user is unable to do login with other email id
    When user login into the application with invalid email as "abhi@yopmail.com"
    Then user should get error message as "Couldn’t find your Google Account"