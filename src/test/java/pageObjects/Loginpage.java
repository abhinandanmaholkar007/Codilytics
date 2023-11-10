package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.GenericUtils;
import static org.testng.Assert.assertEquals;

public class Loginpage {

    WebDriver driver;
    GenericUtils genericUtils;

    public Loginpage(WebDriver driver) {
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

    @FindBy(xpath = "//button[text()='Sign In']")
    WebElement signIn;

    @FindBy(xpath = "//input[@type='email']")
    WebElement emailField;

    @FindBy(xpath = "//span[text()='Next']")
    WebElement nextBtn;


    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordField;

    @FindBy(xpath = "//p[text()='Dashboard']")
    WebElement dashboardTab;

    @FindBy(css = "#headingText span")
    WebElement loginError;

    @FindBy(xpath = "//span[text()='Sign in']")
    WebElement signInLabel;

    @FindBy(id = "headingText")
    WebElement headText;


    /*
     * checkCodilyticsFontFamily method extract the font family and check with expected font family
     */
    public void footerLink()
    {
      termsBtn.isDisplayed();
      privacyBtn.isDisplayed();
    }

    public void TapContactAdmin()
    {
        genericUtils.clickButton(contactAdmin);
    }

    public void contactAdminPage()
    {
        genericUtils.clickButton(contactAdmin);
        genericUtils.SwitchWindowToChild();
        System.out.println(driver.getTitle());
    }


    public void verifyEleColor(String text,String expColour ){
     WebElement ele=driver.findElement(By.xpath("//*[contains(text(), '"+text+"')]"));
        String actColor = genericUtils.getEleColor(ele);
        System.out.println(actColor);
        System.out.println(expColour);
        assertEquals(actColor, expColour);
    }

    public void verifyEleFont(String text,String expFont){
        WebElement element= driver.findElement(By.xpath("(//*[contains(text(),'"+text+"')])[1]"));
        String actSizeAndFont;
        String actFont = genericUtils.getEleFont(element);
        System.out.println(actFont);
        String[] actFontSplit = actFont.split(" ");
        if (actFontSplit[0].equalsIgnoreCase("700")) {
            actSizeAndFont = actFontSplit[1].trim().concat(" "+actFontSplit[4].trim());
        } else {
            actSizeAndFont = actFontSplit[0].trim().concat(" "+actFontSplit[3].trim());
        }

        System.out.println(actSizeAndFont);
        System.out.println(expFont);

        assertEquals(actSizeAndFont, expFont);
    }

    public void signInClick()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        genericUtils.waitUntilElementVisibiltity(codilyticsLabel);
        genericUtils.clickButton(signIn);
        genericUtils.SwitchWindowToChild();
        genericUtils.waitUntilElementClickable(signInLabel);
    }

    public void doLogin(String uname, String password)
    {
      //  genericUtils.waitUntilElementVisibiltity(signInLabel);
        emailField.sendKeys(uname);
        genericUtils.clickButton(nextBtn);
        genericUtils.waitUntilElementClickable(headText);
        passwordField.sendKeys(password);
        genericUtils.clickButton(nextBtn);
    }


    public String verifySuccessfulLogin()
    {

        genericUtils.SwitchtoParentWindow();
         genericUtils.waitUntilElementVisibiltity(dashboardTab);
        return driver.getCurrentUrl();
}
    public void loginWithInvalidEmail(String uname)
    {
        emailField.sendKeys(uname);
        genericUtils.clickButton(nextBtn);
    }
    public String loginErrorForInvalidEmail()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return loginError.getText();
}}
