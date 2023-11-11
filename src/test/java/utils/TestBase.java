package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {
    public WebDriver driver;

    public WebDriver WebDriverManager() {
        // Load properties from the Global.properties file
        Properties globalProperties = loadProperties("Global.properties");

        // Load properties from the extent.properties file
        Properties extentProperties = loadProperties("extends.properties");

        ExtentReports extent = new ExtentReports();

        // Set up the Spark reporter using extent.properties
        if (Boolean.parseBoolean(extentProperties.getProperty("extent.reporter.spark.start"))) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentProperties.getProperty("extent.reporter.spark.out"));
            extent.attachReporter(sparkReporter);
        }

        // You can use other properties from extentProperties for configuration as well
        String screenshotDir = extentProperties.getProperty("screenshot.dir");
        String screenshotRelPath = extentProperties.getProperty("screenshot.rel.path");
        String baseFolderName = extentProperties.getProperty("baseFolder.name");
        String dateTimePattern = extentProperties.getProperty("baseFolder.dateTimePattern");

        String codilyticsURL = globalProperties.getProperty("url");
        String browser = globalProperties.getProperty("browser");

        if (driver == null) {
            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                //firefox code
                driver = new FirefoxDriver();
            }

            assert driver != null;
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.get(codilyticsURL);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return driver;
    }

    private Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//resources//" + fileName);
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
