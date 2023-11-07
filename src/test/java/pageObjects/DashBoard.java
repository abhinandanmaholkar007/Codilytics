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

    @FindBy(xpath = "//button[text()='Terms & Conditions']")
    WebElement termsBtn;

    @FindBy(xpath = "//button[text()='Privacy Policy']")
    WebElement privacyBtn;

    @FindBy(xpath = "//a[text()='Contact Admin']")
    WebElement contactAdmin;

    /*
    * checkCodilyticsFontFamily method extract the font family and check with expected font family
     */

    public String checkCodilyticsFontFamily()
    {
        return codilyticsLabel.getCssValue("font-family");
    }
    public String checkCodilytictsLabelFontSize() {
      return codilyticsLabel.getCssValue("font-size");
    }


    public String checkCodilyticsLabelColor()
    {
        return codilyticsLabel.getCssValue("color");
    }

    public void clickOnTerm()
    {
        genericUtils.clickButton(termsBtn);
    }

    public void clickOnPrivacy()
    {
      genericUtils.clickButton(privacyBtn);
    }

    public void contactAdminBtn()
    {
        genericUtils.clickButton(contactAdmin);
    }



}
