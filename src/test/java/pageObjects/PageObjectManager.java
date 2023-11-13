package pageObjects;

import org.openqa.selenium.WebDriver;
import tech.grasshopper.pdf.section.dashboard.Dashboard;

public class PageObjectManager {
    public WebDriver driver;
    Loginpage loginpage;
    DashBoardPage dashBoardPage;

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

}
