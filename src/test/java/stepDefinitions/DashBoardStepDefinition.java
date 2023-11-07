package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
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

    @Then("check Codilytics label font label as {string}, font size as {string}, color as {string} with the actual data")
    public void check_codilytics_label_font_label_as_font_size_as_color_as(String expFontLabel, String expFontSize, String expColor) {
        String actualFontFamily = dashboard.checkCodilyticsFontFamily();
        System.out.println(actualFontFamily);
        assertEquals(actualFontFamily,expFontLabel);

        String actualFontSize=dashboard.checkCodilytictsLabelFontSize();
        System.out.println(actualFontSize);
        assertEquals(actualFontSize,expFontSize);

        String actualColor=dashboard.checkCodilyticsLabelColor();
        System.out.println(actualColor);
        assertEquals(actualColor,expColor);
    }
    @And("click on the Terms and condition link & verify the url")
    public void click_on_the_Terms_and_condition_link_and_verify_the_url() {
       dashboard.clickOnTerm();
    }

    @And("click on the privacy link & verify the url")
    public void click_on_the_privacy_link_and_verify_the_url()
    {
        dashboard.clickOnPrivacy();
    }

}
