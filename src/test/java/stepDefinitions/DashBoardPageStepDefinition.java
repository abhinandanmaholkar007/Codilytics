package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DashBoardPage;
import utils.TestContextSetUp;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class DashBoardPageStepDefinition {

    TestContextSetUp testContextSetUp;
    DashBoardPage dashBoardPage;

    public DashBoardPageStepDefinition(TestContextSetUp testContextSetUp) {
        this.testContextSetUp = testContextSetUp;
        this.dashBoardPage = testContextSetUp.pageObjectManager.getDashBoardPage();
    }

    @Then("Verify that Welcome Message as {string}")
    public void verify_that_welcome_message_as(String expectedWelMsg) {
        String actualWelMsg = dashBoardPage.welcomeMessage();
        System.out.println(actualWelMsg);
        assertEquals(actualWelMsg, expectedWelMsg);
    }

    @Then("Verify that search bar is displayed")
    public void verify_that_search_bar_is_displayed() {
        dashBoardPage.searchBarIsDisplayed();
    }

    @Then("Verify that Refresh icon is displayed")
    public void verify_that_refresh_icon_is_displayed() {
        dashBoardPage.refreshIconIsDisplayed();
    }

    @Then("Verify that Notification icon is displayed")
    public void verify_that_notification_icon_is_displayed() {
        dashBoardPage.notificationIconIsDisplayed();
    }

    @Then("verify that rupees currency is displayed")
    public void verify_that_rupees_currency_is_displayed() {
        dashBoardPage.rupeesCurrencyBtn();
    }

    @Then("verify that dollar currency is displayed")
    public void verify_that_dollar_currency_is_displayed() {
        dashBoardPage.dollarCurrencyBtn();
    }

    @Then("verify that user is able to tap on customize widget button")
    public void verify_that_user_is_able_to_tap_on_customize_widget_button() {
        dashBoardPage.customizeWidgetBtn();
    }

    @Then("verify that customize widgets options")
    public void verify_that_customize_widgets_options(List<String> expectedCustomizeWidget) {
        dashBoardPage.CustomizeWidgetOption(expectedCustomizeWidget);
    }

    @Then("verify that user is able to view bydefault widgets on dashboard page.")
    public void verify_that_user_is_able_to_view_bydefault_widgets_on_dashboard_page(List<String> expectedDefaultWidgets) {
        dashBoardPage.defaultWidgets(expectedDefaultWidgets);
    }

    @When("user tap on the customize widget icon")
    public void user_tap_on_the_customize_widget_icon() {
        dashBoardPage.refreshPage();
        dashBoardPage.customizeWidgetBtn();

    }

    @When("user enable the toggle of {string}")
    public void user_enable_the_toggle_of_billability_metrics_by_level(String widgetToggleText) {
        dashBoardPage.enableCustomizeWidget(widgetToggleText);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        dashBoardPage.closeCustomizeWidget();
    }

    @Then("verify that the {string} font should be displayed as per {string}")
    public void verify_that_the_font_should_be_displayed_as_per(String element, String sizeFont) {
        dashBoardPage.verifyEleFont(element, sizeFont);
    }

    @Then("verify that the {string} colour should be displayed as per {string}")
    public void verify_that_the_colour_should_be_displayed_as_per(String element, String colour) {

        dashBoardPage.verifyEleColor(element, colour);
    }

    @Then("Verify the Financial Overview Revenue Expense and Profit Percentage per month")
    public void verify_the_financial_overview_renvenue_expense_and_profit_percentage_per_month() {
        dashBoardPage.verifyFinancialOverviewRevenuePercentagePerMonth();
    }

    @Then("Verify the Financial Overview Revenue Expense and Profit Percentage per year")
    public void verify_the_financial_overview_revenue_expense_and_profit_percentage_per_year() {
        dashBoardPage.verifyFinancialOverviewRevenuePercentagePerYear();
    }
}
