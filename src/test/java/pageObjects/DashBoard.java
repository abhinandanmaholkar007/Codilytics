package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.GenericUtils;

public class DashBoard {

    WebDriver driver;
    GenericUtils genericUtils;

    public DashBoard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.genericUtils = new GenericUtils(driver);
    }

    @FindBy(xpath = "//h4[text()='Codilytics']")
    WebElement codilyticsLabel;

    @FindBy(xpath = "//h4[text()='Welcome to our Codilytics Portal']")
    WebElement welcomeText;

    @FindBy(xpath = "//button[text()='Terms & Conditions']")
    WebElement termsBtn;

    @FindBy(xpath = "//button[text()='Privacy Policy']")
    WebElement privacyBtn;

    @FindBy(xpath = "//a[text()='Contact Admin']")
    WebElement contactAdmin;

    @FindBy(xpath = "//button[text()='Sign In']")
    WebElement signIn;


    /*
     * checkCodilyticsFontFamily method extract the font family and check with expected font family
     */

    public String getCodilyticsText() {
        return codilyticsLabel.getText();
    }

    public String checkWelcomeFontFamily() {
        return welcomeText.getCssValue("font-family");
    }

    public String checkWelcomeLabelFontSize() {
        return welcomeText.getCssValue("font-size");
    }


    public String checkWelcomeLabelColor() {
        return welcomeText.getCssValue("color");
    }

    public String getWelcomeText() {
        return welcomeText.getText();
    }

    public String checkCodilyticsFontFamily() {
        return codilyticsLabel.getCssValue("font-family");
    }

    public String checkCodilytictsLabelFontSize() {
        return codilyticsLabel.getCssValue("font-size");
    }


    public String checkCodilyticsLabelColor() {
        return codilyticsLabel.getCssValue("color");
    }

    public void clickOnTerm() {
        genericUtils.clickButton(termsBtn);
    }

    public void clickOnPrivacy() {
        genericUtils.clickButton(privacyBtn);
    }

    public void contactAdminBtn() {
        genericUtils.clickButton(contactAdmin);
    }

    public void signInClick() {
        genericUtils.clickButton(signIn);
    }


}
