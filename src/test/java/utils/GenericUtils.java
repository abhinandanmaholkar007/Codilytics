package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class GenericUtils {
    WebDriver driver;
    JavascriptExecutor executor;

    public GenericUtils(WebDriver driver) {
        this.driver = driver;
    }


    public void clickButton(WebElement btn) {

        executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", btn);
    }

    public void SwitchWindowToChild() {
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        String parentWindow = i1.next();
        String childWindow = i1.next();
        driver.switchTo().window(childWindow);
    }

    public void SwitchtoParentWindow() {
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        String parentWindow = i1.next();
        driver.switchTo().window(parentWindow);
    }


    public void waitUntilElementVisibiltity(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated((By) ele));
    }

    public void login(WebElement dashboardTab) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // genericUtils.SwitchtoParentWindow();
        waitUntilElementClickable(dashboardTab);
    }

    public void waitUntilElementClickable(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public String getEleColor(WebElement webElement) {
        // Get the text color using JavaScript.
        String textColor = ((JavascriptExecutor) driver).executeScript("return window.getComputedStyle(arguments[0]).color", webElement).toString();

        return textColor;
    }

    public String getEleFont(WebElement webElement) {

        // Get the font using JavaScript.
        String actFont = ((JavascriptExecutor) driver).executeScript("return window.getComputedStyle(arguments[0]).font", webElement).toString();
        return actFont;
    }

    public String getEleWidth(WebElement webElement) {
        // Get the dimensions of the WebElement.
        int width = webElement.getSize().getWidth();

        return Integer.toString(width);

    }

    public String getEleheight(WebElement webElement) {
        // Get the dimensions of the WebElement.
        int height = webElement.getSize().getHeight();

        return Integer.toString(height);

    }
}









