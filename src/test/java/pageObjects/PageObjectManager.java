package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    public WebDriver driver;
    Loginpage loginpage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public Loginpage getLoginPage() {
        this.loginpage = new Loginpage(driver);
        return loginpage;
    }

}
