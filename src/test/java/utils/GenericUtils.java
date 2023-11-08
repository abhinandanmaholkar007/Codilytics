package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
}









