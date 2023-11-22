Feature: Verify the Dashboard functionality

  Background: Verify user is on login page
    Given the user is on the login page
    When user login into the application with email as "abhinandan.maholkar@coditas.com" and password as "ndbxf5pqax"
    Then verify that user should be redirect to Dashboard page with url as "https://codilytics.qa.coditas.org/dashboard"

  @verifyWelcomeMessage
  Scenario: Case 1 Verify that Welcome Message with User's First Name
    Then Verify that Welcome Message as "Welcome, Abhinandan"

  @verifyHeaderElement
  Scenario: case 2 Verify the header of the dashboard page consists Search bar, Refresh and Notification icon
    Then Verify that search bar is displayed
    And Verify that Refresh icon is displayed
    And Verify that Notification icon is displayed

  @verifyCurrencyOptions
  Scenario: case 3 verify that currency options are displayed
    Then verify that rupees currency is displayed
    And verify that dollar currency is displayed

  @verifyCustomizeWidgetOptions
  Scenario: case 4 verify that customize widget functionality and their options
    Then  verify that user is able to tap on customize widget button
    And verify that customize widgets options
      | Billability Metrics by Level      |
      | Billability Metrics by Experience |
      | Bench Metrics by Level            |
      | Bench Metrics by Experience       |

  @verifyDefaultWidgets
  Scenario: case 5 verify that default widgets on the dashboard page
    Then  verify that user is able to view bydefault widgets on dashboard page.
      | Financial Overview  |
      | Company Expense     |
      | Billability Metrics |
      | Financial Overview  |
      | Bench               |
      | Active Projects     |
      | Employees           |


  @verifyCustomizeWidgetOptions
  Scenario: case 4 verify that customize widget functionality and their options
    Then  verify that user is able to tap on customize widget button
    And verify that customize widgets options
      | Billability Metrics by Level      |
      | Billability Metrics by Experience |
      | Bench Metrics by Level            |
      | Bench Metrics by Experience       |


  @verifyFontSizeColourOfDashboardWidgetTitle
  Scenario Outline: Verify the font, colour and size of dashboard page titles
    Then verify that the <Element> font should be displayed as per <SizeAndFont>
    And verify that the <Element> colour should be displayed as per <Colour>
    Examples:
      | Element               | SizeAndFont               | Colour             |
      | "Welcome"             | "20px MontSerrat-Regular" | "rgb(14, 14, 14)"  |
      | "Financial Overview"  | "16px MontSerrat-Regular" | "rgb(35, 93, 171)" |
      | "Company Expense"     | "16px MontSerrat-Regular" | "rgb(35, 93, 171)" |
      | "Billability Metrics" | "16px MontSerrat-Regular" | "rgb(35, 93, 171)" |
      | "Bench"               | "16px MontSerrat-Regular" | "rgb(35, 93, 171)" |
      | "Financial Overview"  | "16px MontSerrat-Regular" | "rgb(35, 93, 171)" |
      | "Active Projects"     | "16px MontSerrat-Regular" | "rgb(35, 93, 171)" |
      | "Employees"           | "16px MontSerrat-Regular" | "rgb(35, 93, 171)" |

  @verifyFinancialOverviewRevenuePercentagePerMonth
  Scenario: Verify the Financial Overview Revenue Percentage
    Then Verify the Financial Overview Revenue Expense and Profit Percentage per month

  @verifyFinancialOverviewRevenuePercentagePerYear
  Scenario: Verify the Financial Overview Revenue Percentage
    Then Verify the Financial Overview Revenue Expense and Profit Percentage per year


  @verifyCompanyExpensePercentagePerMonth
  Scenario: Verify the Company Expense Percentage per month
    Then Verify the Company Expense CTC and Admin Percentage per month

  @verifyCompanyExpensePercentagePerYear
  Scenario: Verify the Company Expense Percentage per year
    Then Verify the Company Expense CTC and Admin Percentage per year

  @verifyBillabiltityMetricsPercentagePerMonth
  Scenario: Verify the Billability Metrics Percentage per month
    Then Verify the Billability Metrics CTC and Admin Percentage per month

  @verifyBillabilityMetricsPercentagePerYear
  Scenario: Verify the Billability Metrics Percentage per year
    Then Verify the Billability Metrics CTC and Admin Percentage per year

  @verifyActiveProjectsPercentage
  Scenario: Verify the active projects Percentage per month
    Then Verify the total client and internal active projects Percentage per month

  @verifyEmployeesPercentage
  Scenario: Verify the Employees Percentage per year
    Then Verify the total on notice and new hire Employees Percentage per month

  @verifyBenchPercentage
  Scenario: Verify the Bench Percentage per year
    Then Verify the Total Bench, Usable Bench, Reserved, Trainee and upcoming bench Percentage per month

  @VerifyInformativeIcon
  Scenario Outline: Verify informative icon
    Then Verify the "<icon>" should display informative message
    Examples:
      | icon                |
      | Billability Metrics |
      | Total Bench         |
      | Usable Bench        |
      | Reserved            |
      | Trainees            |
      | Upcoming Bench      |

  @VerifyTotalRevenue
  Scenario: verify and calculate total revenue
    Then calculate total revenue
