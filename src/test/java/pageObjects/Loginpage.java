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

    @FindBy(xpath = "//div[@class='o6cuMc Jj6Lae']")
    WebElement loginError;

    //footerLink method is used to check whether the footer link is displayed or not in login page.
    public void footerLink() {
        termsBtn.isDisplayed();
        privacyBtn.isDisplayed();
    }

    /*
     *TapContactAdmin() is used to click on the Contact Admin button
     * clickButton() is created in GenericUtil class which is commonly used for click operations
     */
    public void TapContactAdmin() {
        genericUtils.clickButton(contactAdmin);
    }

    /*
     *contactAdminPage() verify whether user is able to redirect to the coditas compose mailbox or not
     * SwitchWindowToChild() is common method used to handle child window which is created in GenraricUtils class
     */
    public void contactAdminPage() {
        genericUtils.SwitchWindowToChild();
        System.out.println(driver.getTitle());
    }

    /*
     *verifyEleColor() is used to verify the color of all element label which is present on login page
     * getEleColor() is common method used to extract color of the element which is created in GeneraicUtils class
     */
    public void verifyEleColor(String text, String expColour) {
        WebElement ele = driver.findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
        String actColor = genericUtils.getEleColor(ele);
        System.out.println(actColor);
        System.out.println(expColour);
        assertEquals(actColor, expColour);
    }

    /*
     *verifyEleFont() is used to verify the font family and font size of all the label which is present on the login page
     * getEleFont() is common method used to extract font family and size of the element which is created in GeneraicUtils class
     */
    public void verifyEleFont(String text, String expFont) {
        WebElement element = driver.findElement(By.xpath("(//*[contains(text(),'" + text + "')])[1]"));
        String actSizeAndFont;
        String actFont = genericUtils.getEleFont(element);
        System.out.println(actFont);
        String[] actFontSplit = actFont.split(" ");
        if (actFontSplit[0].equalsIgnoreCase("700")) {
            actSizeAndFont = actFontSplit[1].trim().concat(" " + actFontSplit[4].trim());
        } else {
            actSizeAndFont = actFontSplit[0].trim().concat(" " + actFontSplit[3].trim());
        }

        System.out.println(actSizeAndFont);
        System.out.println(expFont);

        assertEquals(actSizeAndFont, expFont);
    }

    /*
     *signInClick() is used to click on the signin button
     *clickButton() is common method used to click on the element, which is created in GeneraicUtils class
     *SwitchWindowToChild() is common method used to switch selenium focus to child window, which is created in GeneraicUtils class
     */
    public void signInClick() {
        genericUtils.clickButton(signIn);
        genericUtils.SwitchWindowToChild();
    }

    // doLogin() is used to enter id and password for login
    public void doLogin(String uname, String password) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        emailField.sendKeys(uname);
        genericUtils.clickButton(nextBtn);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        passwordField.sendKeys(password);
        genericUtils.clickButton(nextBtn);
    }

    //verifyLogin() is used to verify the successful login and extract the title of the dashboard page
    public void verifyLogin(String expDashUrl) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // genericUtils.SwitchtoParentWindow();
        genericUtils.waitUntilElementClickable(dashboardTab);
        String actDashUrl = driver.getCurrentUrl();
        assertEquals(actDashUrl, expDashUrl);
    }

    //loginWithInvalidEmail() is used to enter invalid credential
    public void loginWithInvalidEmail(String uname) {
        signInClick();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        emailField.sendKeys(uname);
        genericUtils.clickButton(nextBtn);
    }

    //loginErrorForInvalidEmail() is used to extract login error message
    public String loginErrorForInvalidEmail() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return loginError.getText();
    }
}
