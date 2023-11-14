package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.GenericUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class DashBoardPage {

    WebDriver driver;
    GenericUtils genericUtils;

    public DashBoardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.genericUtils = new GenericUtils(driver);
    }

    @FindBy(xpath = "//div[@class='_heading_x7m42_20']")
    WebElement welcomeMsg;

    @FindBy(css = "[placeholder ='Search Employee']")
    WebElement searchBar;

    @FindBy(css = "#mask0_10228_7161 rect")
    WebElement refreshIcon;

    @FindBy(id = "Vector")
    WebElement notificationIcon;

    @FindBy(id = "mask0_8384_7496")
    WebElement rupeesCurrency;

    @FindBy(id = "mask0_8384_7500")
    WebElement dollarCurrency;

    @FindBy(xpath = "//button[contains(@class,'addChartsIconButton')]")
    WebElement customizeWidgetBtn;

    @FindBy(xpath = "//div[contains(@class,'_customWidgetContainer')]")
    List<WebElement> customWidgetContainer;

    public long currentYearRevenue;
    public long lastYearRevenue;

    public String enabledWidget;
    public long lastMonthRevenue;


    @FindBy(xpath = "//div[@id='drawerMainContainier']//div[@class='_operationContainer_11g3y_29 undefined']//*[name()='svg']")
    WebElement closeCustomizeWidget;

    @FindBy(xpath = "//div[@class='_widgetWrapper_xym9g_42'])[1]/div")
    List<WebElement> widgetWrapper;

    @FindBy(xpath = "//p[text()='Financial Overview']")
    WebElement financialRevenue;

    public long currentRevenue;

    /*
     *When user navigate to the dashboard page welcome+user's first name must have to display
     *welcomeMessage() verify the expected and actual welcome msg is matched or not
     */
    public String welcomeMessage() {
        return welcomeMsg.getText();
    }

    public void searchBarIsDisplayed() {
        searchBar.isDisplayed();
    }

    public void refreshIconIsDisplayed() {
        refreshIcon.isDisplayed();
    }

    public void notificationIconIsDisplayed() {
        notificationIcon.isDisplayed();
    }

    public void rupeesCurrencyBtn() {
        rupeesCurrency.isDisplayed();
    }

    public void dollarCurrencyBtn() {
        dollarCurrency.isDisplayed();

    }

    /*
     * customizeWidgetBtn() check if widget is present enable it.
     */
    public void customizeWidgetBtn() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        customizeWidgetBtn.isDisplayed();
        customizeWidgetBtn.click();
    }

    public void CustomizeWidgetOption(List<String> expectedCustomizeWidget) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> customizeWidgetOptionsList = customWidgetContainer;
        List<String> actualCustomizeWidget = new ArrayList<>();
        for (WebElement customizeWidget : customizeWidgetOptionsList) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            actualCustomizeWidget.add((String) js.executeScript("return arguments[0].innerText", customizeWidget));
        }

        System.out.println(actualCustomizeWidget.size() == expectedCustomizeWidget.size()
                && actualCustomizeWidget.containsAll(expectedCustomizeWidget));
    }

    public void defaultWidgets(List<String> expectedDefaultWidgets) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> DefaultWidgetList = driver.findElements(By.xpath("//div[contains(@class,'_titleSection')]"));
        List<String> actualDefaultWidget = new ArrayList<>();

        for (WebElement defaultWidget : DefaultWidgetList) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            actualDefaultWidget.add((String) js.executeScript("return arguments[0].innerText;", defaultWidget));

        }
        String[] split = actualDefaultWidget.get(3).split("2");
        String actualFinancialOverview = split[0].trim();
        actualDefaultWidget.remove(3);
        actualDefaultWidget.add(3, actualFinancialOverview);

        System.out.println((actualDefaultWidget.size() == expectedDefaultWidgets.size())
                && actualDefaultWidget.containsAll(expectedDefaultWidgets));
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void enableCustomizeWidget(String widgetToggleText) {
        List<WebElement> customizeWidgetToggles = driver.findElements(By.xpath("(//div[contains(@class,'_customWidgetContainer')]/p)"));
        customizeWidgetToggles.add(0, driver.findElement(By.xpath("//div[contains(@class,'_customWidgetContainer')][1]/p")));
        for (int i = 1; i < customizeWidgetToggles.size(); i++) {
            String getWidgettext = driver.findElement(By.xpath("(//div[contains(@class,'_customWidgetContainer')]/p)[" + i + "]")).getText();
            if (getWidgettext.equalsIgnoreCase(widgetToggleText)) {
                driver.findElement(By.xpath("(//div[contains(@class,'_customWidgetContainer')]/p)[" + i + "]/following-sibling::div//span")).click();
                enabledWidget = widgetToggleText;
                break;
            }
        }

    }

    public void closeCustomizeWidget() {
        closeCustomizeWidget.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyEleFont(String text, String expFont) {

        WebElement element = driver.findElement(By.xpath("(//*[contains(text(),'" + text + "')])[1]"));
        String actSizeAndFont;
        String actFont = genericUtils.getEleFont(element);
        String[] actFontSplit = actFont.split(" ");
        if (actFontSplit[0].equalsIgnoreCase("700")) {
            actSizeAndFont = actFontSplit[1].trim().concat(" " + actFontSplit[4].trim());
        } else if (actFontSplit[0].equalsIgnoreCase("600")) {
            actSizeAndFont = actFontSplit[1].trim().concat(" " + actFontSplit[4].trim());
        } else if (actFontSplit[0].equalsIgnoreCase("500")) {
            actSizeAndFont = actFontSplit[1].trim().concat(" " + actFontSplit[4].trim());
        } else {
            actSizeAndFont = actFontSplit[0].trim().concat(" " + actFontSplit[3].trim());
        }

        System.out.println(actSizeAndFont);
        System.out.println(expFont);
        assertEquals(actSizeAndFont, expFont);
    }

    public void verifyEleColor(String text, String expColour) {
        WebElement element = driver.findElement(By.xpath("(//*[contains(text(),'" + text + "')])[1]"));
        String actColor = genericUtils.getEleColor(element);
        System.out.println(actColor);
        System.out.println(expColour);
        assertEquals(actColor, expColour);
    }

    public void verifyFinancialOverviewRevenuePercentagePerMonth() {
        String actualRevenuePercentage;
        driver.navigate().refresh();
        List<WebElement> actualRevenuePercent = widgetWrapper;
        actualRevenuePercent.add(0, financialRevenue);
        for (int i = 1; i < actualRevenuePercent.size(); i++) {
            String[] actualRevenuePercentageText = driver
                    .findElement(By.xpath("(//div[@class='_widgetWrapper_xym9g_42'])[1]/div[" + i + "]//span[@style]")).getText()
                    .split("%");
            actualRevenuePercentage = actualRevenuePercentageText[0];
            System.out.println(actualRevenuePercentage);
            Actions act = new Actions(driver);
            act.moveToElement(
                            driver.findElement(By.xpath("(//div[@class='_widgetWrapper_xym9g_42'])[1]/div[" + i + "]//span[@style]")))
                    .perform();
            String toolTipData = driver.findElement(By.xpath("(//div[@class='_widgetWrapper_xym9g_42'])[1]/div[" + i + "]//div[@class='_timeWrapper_xym9g_95']/div[3]")).getText();
            if (toolTipData.contains(".")) {
                String tooltip = toolTipData.replace(".", "");
                String[] tooltipValues = tooltip.split(" ");
                String getCurrentrevenue = tooltipValues[1];
                String[] currentRevenue1 = getCurrentrevenue.split("V");
                String currentRevenueValue = currentRevenue1[0].trim().replace(",", "");
                String getLastMonthRevenue = tooltipValues[4].trim().replace(",", "");
                currentRevenue = Long.parseLong(currentRevenueValue);
                lastMonthRevenue = Long.parseLong(getLastMonthRevenue);
            } else {
                String[] tooltipValues = toolTipData.split(" ");
                String getCurrentrevenue = tooltipValues[1];
                String[] currentRevenue1 = getCurrentrevenue.split("V");
                String currentRevenueValue = currentRevenue1[0].trim().replace(",", "");
                String getLastMonthRevenue = tooltipValues[4].trim().replace(",", "");
                currentRevenue = Long.parseLong(currentRevenueValue);
                lastMonthRevenue = Long.parseLong(getLastMonthRevenue);
            }

            DecimalFormat df = new DecimalFormat("0.00");

            long differenceRevenue = currentRevenue - lastMonthRevenue;

            System.out.println(differenceRevenue);

            double revenuePercent = Math.floor(differenceRevenue * 100) / lastMonthRevenue;


            df.setRoundingMode(RoundingMode.UP);
            System.out.println("double (RoundingMode.UP)  : " + df.format(revenuePercent));

            if (!df.format(revenuePercent).contains(actualRevenuePercentage)) {
                System.out.println(true);
            }
            System.out.println("-----------------------------------------------------------");
        }
    }

    public void verifyFinancialOverviewRevenuePercentagePerYear() {
        String actualRevenuePercentage;
        driver.navigate().refresh();
        List<WebElement> actualRevenuePercent = driver
                .findElements(By.xpath("(//div[@class='_widgetWrapper_xym9g_42'])[2]/div"));
        actualRevenuePercent.add(0, driver.findElement(By.xpath("//p[text()='Financial Overview']")));
        for (int i = 1; i < actualRevenuePercent.size(); i++) {
            String[] actualRevenuePercentageText = driver
                    .findElement(By.xpath("(//div[@class='_widgetWrapper_xym9g_42'])[2]/div[" + i + "]//span[@style]")).getText()
                    .split("%");
            actualRevenuePercentage = actualRevenuePercentageText[0];
            Actions act = new Actions(driver);
            act.moveToElement(
                            driver.findElement(By.xpath("(//div[@class='_widgetWrapper_xym9g_42'])[2]/div[" + i + "]//span[@style]")))
                    .perform();
            String toolTipData = driver.findElement(By.xpath("(//div[@class='_widgetWrapper_xym9g_42'])[2]/div[" + i + "]//div[@class='_timeWrapper_xym9g_95']/div[2]/div[2]")).getText();
            if (toolTipData.contains(".")) {
                String tooltip = toolTipData.replace(".", "");
                String[] tooltipValues = tooltip.split(" ");
                String getCurrentrevenue = tooltipValues[1];
                String[] currentRevenue1 = getCurrentrevenue.split("V");
                String currentRevenueValue = currentRevenue1[0].trim().replace(",", "");
                String getLastMonthRevenue = tooltipValues[5].trim().replace(",", "");
                currentYearRevenue = Long.parseLong(currentRevenueValue);
                lastYearRevenue = Long.parseLong(getLastMonthRevenue);
            } else {
                String[] tooltipValues = toolTipData.split(" ");
                String getCurrentrevenue = tooltipValues[1];
                String[] currentRevenue1 = getCurrentrevenue.split("V");
                String currentRevenueValue = currentRevenue1[0].trim().replace(",", "");
                String getLastMonthRevenue = tooltipValues[4].trim().replace(",", "");
                currentYearRevenue = Long.parseLong(currentRevenueValue);
                lastYearRevenue = Long.parseLong(getLastMonthRevenue);
            }

            DecimalFormat df = new DecimalFormat("0.00");

            long differenceRevenue = currentYearRevenue - lastYearRevenue;

            System.out.println(differenceRevenue);

            double revenuePercent = Math.floor(differenceRevenue * 100) / lastYearRevenue;

            df.setRoundingMode(RoundingMode.UP);
            System.out.println("double (RoundingMode.UP)  : " + df.format(revenuePercent));


            if (df.format(revenuePercent).contains(actualRevenuePercentage)) {
                System.out.println(true);
            } else if (df.format(revenuePercent).equals("∞")) {
                System.out.println("0 %");
            }
        }
    }

}
