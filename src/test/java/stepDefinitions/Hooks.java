package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.TestContextSetUp;

import java.io.File;
import java.io.IOException;

public class Hooks {
    TestContextSetUp testContextSetUp;
    WebDriver driver;

    public Hooks(TestContextSetUp testContextSetUp) {
        this.testContextSetUp = testContextSetUp;
    }

    /*
     * afterScenario method quit(close) the browser after running every scenario.
     */
    @After
    public void afterScenario() {
        testContextSetUp.testBase.WebDriverManager().quit();
    }

    /*
     * addScreenShots method takes the screenshot if any script gets failed
     */
    @AfterStep
    public void addScreenShots(Scenario scenario) {
        driver = testContextSetUp.testBase.WebDriverManager();
        if (scenario.isFailed()) {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);


            byte[] fileContent;
            try {
                fileContent = FileUtils.readFileToByteArray(source);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            scenario.attach(fileContent, "image/png", "image");

        }
    }
}
