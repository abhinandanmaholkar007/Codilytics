package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    public WebDriver driver;
    DashBoard dashBoard;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public DashBoard getDashBoardPage() {
        this.dashBoard = new DashBoard(driver);
        return dashBoard;
    }

}
