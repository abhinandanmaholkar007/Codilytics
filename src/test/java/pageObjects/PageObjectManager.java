package pageObjects;

import org.openqa.selenium.WebDriver;


public class PageObjectManager {
    public WebDriver driver;
    Loginpage loginpage;
    DashBoardPage dashBoardPage;
    BenchPage benchPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public Loginpage getLoginPage() {
        this.loginpage = new Loginpage(driver);
        return loginpage;
    }

    public DashBoardPage getDashBoardPage() {
        this.dashBoardPage = new DashBoardPage(driver);
        return dashBoardPage;
    }

    public BenchPage getBenchPage() {
        this.loginpage = new Loginpage(driver);
        return benchPage;
    }

}
