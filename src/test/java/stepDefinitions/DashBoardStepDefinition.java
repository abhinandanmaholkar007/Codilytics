package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DashBoard;
import utils.TestContextSetUp;

import static org.testng.Assert.assertEquals;

public class DashBoardStepDefinition {

    TestContextSetUp testContextSetUp;
    DashBoard dashboard;

    public DashBoardStepDefinition(TestContextSetUp testContextSetUp) {
        this.testContextSetUp = testContextSetUp;
        this.dashboard = testContextSetUp.pageObjectManager.getDashBoardPage();
    }


    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        testContextSetUp.testBase.WebDriverManager();
    }

    @When("user go to the login page codilytics label should be present and matched with actual text {string}")
    public void user_go_to_the_login_page_codilytics_label_should_be_present(String expCoditylicsText) {
        String actualCodilyticsText = dashboard.getCodilyticsText();
        System.out.println(actualCodilyticsText);
        assertEquals(actualCodilyticsText, expCoditylicsText);
    }

    @Then("check Codilytics label font label as {string}, font size as {string}, color as {string} with the actual design")
    public void check_codilytics_label_font_label_as_font_size_as_color_as(String expFontLabel, String expFontSize, String expColor) {
        String actualFontFamily = dashboard.checkCodilyticsFontFamily();
        System.out.println(actualFontFamily);
        assertEquals(actualFontFamily, expFontLabel);

        String actualFontSize = dashboard.checkCodilytictsLabelFontSize();
        System.out.println(actualFontSize);
        assertEquals(actualFontSize, expFontSize);

        String actualColor = dashboard.checkCodilyticsLabelColor();
        System.out.println(actualColor);
        assertEquals(actualColor, expColor);
    }

    @And("extract the actual text {string} with expected text")
    public void extract_the_actual_text_with_expected_text(String expWelcomeText) {
        String actualWelcomeText = dashboard.getWelcomeText();
        System.out.println(actualWelcomeText);
        assertEquals(actualWelcomeText, expWelcomeText);
    }

    @Then("check welcome text label's font label as {string}, font size as {string}, color as {string} with the actual design")
    public void check_welcome_text_label_with_the_actual_design(String expFontLabel, String expFontSize, String expColor) {
        String actualFontFamily = dashboard.checkWelcomeFontFamily();
        System.out.println(actualFontFamily);
        assertEquals(actualFontFamily, expFontLabel);

        String actualFontSize = dashboard.checkWelcomeLabelFontSize();
        System.out.println(actualFontSize);
        assertEquals(actualFontSize, expFontSize);

        String actualColor = dashboard.checkWelcomeLabelColor();
        System.out.println(actualColor);
        assertEquals(actualColor, expColor);
    }

    @Then("click on the Terms and condition link & verify the url")
    public void click_on_the_Terms_and_condition_link_and_verify_the_url() {
        dashboard.clickOnTerm();
    }

    @Then("click on the privacy link & verify the url")
    public void click_on_the_privacy_link_and_verify_the_url() {
        dashboard.clickOnPrivacy();
    }

    @Then("click on contact admin button and navigate to coditas compose mail")
    public void click_on_contact_admin_button_and_navigate_to_coditas_compose_mail() {
        dashboard.contactAdminBtn();
    }

    @When("user clicks on the sign in button")
    public void user_clicks_on_the_sign_in_button() {
        dashboard.signInClick();
    }

    @Then("user should be logged in with coditas mail id and dashboard page should be displayed")
    public void user_should_be_logged_in_with_coditas_mail_id_and_dashboard_page_should_be_displayed() {

    }

}
