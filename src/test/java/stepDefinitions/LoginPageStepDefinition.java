package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.Loginpage;
import utils.TestContextSetUp;

import static org.testng.Assert.assertEquals;

public class LoginPageStepDefinition {

    TestContextSetUp testContextSetUp;
    Loginpage loginpage;

    public LoginPageStepDefinition(TestContextSetUp testContextSetUp) {
        this.testContextSetUp = testContextSetUp;
        this.loginpage = testContextSetUp.pageObjectManager.getLoginPage();
    }


    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        testContextSetUp.testBase.WebDriverManager();
    }

    @Then("verify that the {string} font should be displayed as {string}")
    public void verify_that_the_font_should_be_displayed_as(String element, String sizeFont) {
        loginpage.verifyEleFont(element, sizeFont);
    }

    @And("verify that the {string} colour should be displayed as {string}")
    public void verify_that_the_colour_should_be_displayed_as(String element, String colour) {
        loginpage.verifyEleColor(element, colour);
    }


    @Then("verify that terms and condition link and Privacy policy should be displayed")
    public void verify_that_terms_and_condition_link_should_be_displayed() {
        loginpage.footerLink();
    }

    @Then("verify that user should be able to tap on contact admin link")
    public void verify_that_user_should_be_able_to_tap_on_contact_admin_link() {
        loginpage.TapContactAdmin();
    }

    @Then("verify that user should redirect to Gmail login")
    public void verify_that_user_should_redirect_to_login() {
        loginpage.contactAdminPage();
    }

    @When("user login into the application with email as {string} and password as {string}")
    public void user_login_into_the_application_with_email(String email, String password) {
        loginpage.signInClick();
        loginpage.doLogin(email, password);
    }

    @Then("verify that user should be redirect to Dashboard page with url as {string}")
    public void verify_that_user_should_be_redirect_to_Dashboard_page_with_url_as(String expDashUrl) {
        loginpage.verifyLogin(expDashUrl);
    }

    @When("user login into the application with invalid email as {string}")
    public void user_login_into_the_application_with_invalid_email_as(String email) {
        loginpage.loginWithInvalidEmail(email);
    }

    @Then("user should get error message as {string}")
    public void user_should_get_error_message_as(String expErrorMsg) {
        String actErrorMsg = loginpage.loginErrorForInvalidEmail();
        System.out.println(actErrorMsg);
        assertEquals(actErrorMsg, expErrorMsg);
    }
}
