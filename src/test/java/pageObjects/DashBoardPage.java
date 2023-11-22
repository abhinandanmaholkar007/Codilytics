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

    @FindBy(xpath = "//div[contains(@class,'_heading')]")
    WebElement welcomeMsg;

    @FindBy(css = "[placeholder ='Search Employee']")
    WebElement searchBar;

    @FindBy(css = "#mask0_10228_7161 rect")
    WebElement refreshIcon;

    @FindBy(id = "Vector")
    WebElement notificationIcon;

    @FindBy(xpath = "//*[@id='mask0_8384_7496']")
    WebElement rupeesCurrency;

    @FindBy(id = "//*[@id='mask0_8384_7500']")
    WebElement dollarCurrency;

    @FindBy(xpath = "//button[contains(@class,'addChartsIconButton')]")
    WebElement customizeWidgetBtn;

    @FindBy(xpath = "//div[contains(@class,'_titleSection')]")
    List<WebElement> DefaultWidgetList;

    @FindBy(xpath = "//div[contains(@class,'_customWidgetContainer')]")
    List<WebElement> customWidgetContainer;

    @FindBy(xpath = "//div[contains(@class,'_customWidgetContainer')]/p")
    List<WebElement> customizeWidgetToggle;

    @FindBy(xpath = "//div[contains(@class,'_customWidgetContainer')][1]/p")
    WebElement addWidget;

    @FindBy(xpath = "//div[contains(@class,'_contentContainer_16')]")
    WebElement infoIcon;

    @FindBy(xpath = "//span[contains(@class,'_slider')]")
    WebElement enableBtn;

    public long currentYearRevenue;
    public long lastYearRevenue;

    public String enabledWidget;
    public long lastMonthRevenue;
    public double allRevenue=83.7;
    public double allexpenses=27.8;
    public double profit;
    public double loss;


    @FindBy(xpath = "//div[@id='drawerMainContainier']//div[@class='_operationContainer_11g3y_29 undefined']//*[name()='svg']")
    WebElement closeCustomizeWidget;

    @FindBy(xpath = "//div[@class='_widgetWrapper_xym9g_42'])[1]/div")
    List<WebElement> actualRevenuePercent;

    @FindBy(xpath = "//p[text()='Financial Overview']")
    WebElement financialRevenue;

    @FindBy(xpath = "//p[@class='_paragraph_k06lb_1 _regular_k06lb_4 _description_17lct_11']")
    WebElement noDataBillabilityMetrics;
    public long currentRevenue;

    /*
     *When user navigate to the dashboard page welcome+user's first name must have to display
     *welcomeMessage() verify the expected and actual welcome msg is matched or not
     */
    public String welcomeMessage() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
        genericUtils.clickButton(welcomeMsg);
        rupeesCurrency.isDisplayed();
    }

    public void dollarCurrencyBtn() {
        genericUtils.clickButton(welcomeMsg);
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

    /*
     * CustomizeWidgetOption() check the size and name of the widget
     */
    public void CustomizeWidgetOption(List<String> expectedCustomizeWidget) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<String> actualCustomizeWidget = new ArrayList<>();
        for (WebElement customizeWidget : customWidgetContainer) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            actualCustomizeWidget.add((String) js.executeScript("return arguments[0].innerText", customizeWidget));
        }

        System.out.println(actualCustomizeWidget.size() == expectedCustomizeWidget.size()
                && actualCustomizeWidget.containsAll(expectedCustomizeWidget));
    }

    /*
     * defaultWidgets() check the size and name of the widget on dashBoard
     */
    public void defaultWidgets(List<String> expectedDefaultWidgets) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    /*
     * enableCustomizeWidget() check by enabling the widget
     */
    public void enableCustomizeWidget(String widgetToggleText) {

        customizeWidgetToggle.add(0, addWidget);
        for (int i = 1; i < customizeWidgetToggle.size(); i++) {
            String getWidgettext = driver.findElement(By.xpath("(//div[contains(@class,'_customWidgetContainer')]/p)[" + i + "]")).getText();
            if (getWidgettext.equalsIgnoreCase(widgetToggleText)) {
                driver.findElement(By.xpath("(//div[contains(@class,'_customWidgetContainer')]/p)[" + i + "]/following-sibling::div//span")).click();
                enabledWidget = widgetToggleText;
                break;
            }
        }
    }

    /*
     * closeCustomizeWidget() close the widget
     */
    public void closeCustomizeWidget() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        closeCustomizeWidget.click();

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

    /*
     * verifyEleColor() check the font family,size and color of all label present on the dashboardpage
     */
    public void verifyEleColor(String text, String expColour) {
        WebElement element = driver.findElement(By.xpath("(//*[contains(text(),'" + text + "')])[1]"));
        String actColor = genericUtils.getEleColor(element);
        System.out.println(text);
        System.out.println(actColor);
        System.out.println(expColour);
        assertEquals(actColor, expColour);
    }

    /*
     * verifyFinancialOverviewRevenuePercentagePerMonth() test below points
     * calculate the profit and loss by month
     * and show red and green indication and arrow
     */
    public void verifyFinancialOverviewRevenuePercentagePerMonth() {
        String actualRevenuePercentage = null;
        driver.navigate().refresh();
        List<WebElement> actualRevenuePercent = driver
                .findElements(By.xpath("//div[contains(@class,'_widgetWrapper')][1]/div"));

        actualRevenuePercent.add(0, driver.findElement(By.xpath("//p[text()='Financial Overview']")));
        for (int i = 1; i < actualRevenuePercent.size(); i++) {
            String[] actualRevenuePercentageText = driver
                    .findElement(By.xpath("//div[contains(@class,'_widgetWrapper')][1]/div["+i+"]//span[@style]")).getText()
                    .split("%");
            actualRevenuePercentage = actualRevenuePercentageText[0];
            System.out.println(actualRevenuePercentage);
            Actions act = new Actions(driver);
            act.moveToElement(
                            driver.findElement(By.xpath("(//div[@class='_widgetWrapper_xym9g_42'])[1]/div["+i+"]//span[@style]")))
                    .perform();
            String toolTipData = driver.findElement(By.xpath("(//div[@class='_widgetWrapper_xym9g_42'])[1]/div["+i+"]//div[@class='_timeWrapper_xym9g_95']/div[3]")).getText();
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
    /*
     * verifyFinancialOverviewRevenuePercentagePerYear() test below points
     * calculate the profit and loss by yearly basis
     * and show red and green indication and arrow
     */
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

    public void verifyCompanyExpensePercentagePerMonth() {
        String actualRevenuePercentage = null;
        driver.navigate().refresh();
        List<WebElement> actualRevenuePercent = driver
                .findElements(By.xpath("(//div[@class='_widgetWrapper_100me_37'])[1]/div"));
        actualRevenuePercent.add(0, driver.findElement(By.xpath("//p[text()='Company Expense']")));
        for (int i = 1; i < actualRevenuePercent.size(); i++) {
            String[] actualRevenuePercentageText = driver
                    .findElement(By.xpath("(//div[@class='_widgetWrapper_100me_37'])[1]/div["+i+"]//span[@style]")).getText()
                    .split("%");
            actualRevenuePercentage = actualRevenuePercentageText[0];
            System.out.println(actualRevenuePercentage);
            Actions act = new Actions(driver);
            act.moveToElement(
                            driver.findElement(By.xpath("(//div[@class='_widgetWrapper_100me_37'])[1]/div["+i+"]//span[@style]")))
                    .perform();
            String toolTipData = driver.findElement(By.xpath("(//div[@class='_widgetWrapper_100me_37'])[1]/div["+i+"]//div[@class='_timeWrapper_100me_68']/div[3]")).getText();
            System.out.println(toolTipData);
            if (toolTipData.contains(".")) {
                String tooltip = toolTipData.replace(".", "");

                String[] tooltipValues = tooltip.split(" ");
                String getCurrent = tooltipValues[1];
                String[] current = getCurrent.split("V");
                String currentValue = current[0].trim().replace(",", "");
                String getLastMonth = tooltipValues[4].trim().replace(",", "");
                currentRevenue = Long.parseLong(currentValue);
                lastMonthRevenue = Long.parseLong(getLastMonth);
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
        }}

        public void verifyCompanyExpensePercentagePerYear() {
            String actualRevenuePercentage = null;
            driver.navigate().refresh();
            List<WebElement> actualRevenuePercent = driver
                    .findElements(By.xpath("(//div[@class='_widgetWrapper_100me_37'])[2]/div"));
            actualRevenuePercent.add(0, driver.findElement(By.xpath("//p[text()='Company Expense']")));
            for (int i = 1; i < actualRevenuePercent.size(); i++) {
                String[] actualRevenuePercentageText = driver
                        .findElement(By.xpath("(//div[@class='_widgetWrapper_100me_37'])[2]/div["+i+"]//span[@style]")).getText()
                        .split("%");
                actualRevenuePercentage = actualRevenuePercentageText[0];
                Actions act = new Actions(driver);
                act.moveToElement(
                                driver.findElement(By.xpath("(//div[@class='_widgetWrapper_100me_37'])[2]/div["+i+"]//span[@style]")))
                        .perform();
                String toolTipData = driver.findElement(By.xpath("(//div[@class='_widgetWrapper_100me_37'])[2]/div["+i+"]//div[@class='_timeWrapper_100me_68']/div[3]")).getText();
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
                }
                else if (df.format(revenuePercent).equals("∞")) {
                    System.out.println("0 %");
                }
                else {
                    System.out.println(false);
                }

            }
        }

    public void verifyBillabilityMetricsPercentagePerMonth() {
        driver.navigate().refresh();
        if (noDataBillabilityMetrics.isDisplayed()) {
            System.out.println(noDataBillabilityMetrics.getText());
        } else {
            String actualRevenuePercentage = null;
            List<WebElement> actualRevenuePercent = driver
                    .findElements(By.xpath("(//div[@class='_widgetWrapper_1qgz5_33'])[1]/div"));
            actualRevenuePercent.add(0, driver.findElement(By.xpath("//p[text()='Billability Metrics']")));
            for (int i = 1; i < actualRevenuePercent.size(); i++) {
                String[] actualRevenuePercentageText = driver
                        .findElement(By.xpath("(//div[@class='_widgetWrapper_1qgz5_33'])[1]/div["+i+"]//span[@style]")).getText()
                        .split("%");
                actualRevenuePercentage = actualRevenuePercentageText[0];
                System.out.println(actualRevenuePercentage);
                Actions act = new Actions(driver);
                act.moveToElement(
                                driver.findElement(By.xpath("(//div[@class='_widgetWrapper_1qgz5_33'])[1]/div["+i+"]//span[@style]")))
                        .perform();
                String toolTipData = driver.findElement(By.xpath("(//div[@class='_widgetWrapper_1qgz5_33'])[1]/div["+i+"]//div[@class='_timeWrapper_100me_68']/div[3]")).getText();
                System.out.println(toolTipData);
                if (toolTipData.contains(".")) {
                    String tooltip = toolTipData.replace(".", "");

                    String[] tooltipValues = tooltip.split(" ");
                    String getCurrent = tooltipValues[1];
                    String[] current = getCurrent.split("V");
                    String currentValue = current[0].trim().replace(",", "");
                    String getLastMonth = tooltipValues[4].trim().replace(",", "");
                    currentRevenue = Long.parseLong(currentValue);
                    lastMonthRevenue = Long.parseLong(getLastMonth);
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

//						df.setRoundingMode(RoundingMode.DOWN);
//						System.out.println("\ndouble (RoundingMode.DOWN) : " + df.format(revenuePercent));

                df.setRoundingMode(RoundingMode.UP);
                System.out.println("double (RoundingMode.UP)  : " + df.format(revenuePercent));

                if (df.format(revenuePercent).contains(actualRevenuePercentage)) {
                    System.out.println(true);
                }
                else if (df.format(revenuePercent).equals("∞")) {
                    System.out.println("0 %");
                }
                else {
                    System.out.println(false);
                }
            }
        }

    }


    public void verifyBillabilityMetricsPercentagePerYear() {
        driver.navigate().refresh();
        if (noDataBillabilityMetrics.isDisplayed()) {
            System.out.println(noDataBillabilityMetrics.getText());
        } else {
            String actualRevenuePercentage = null;

            List<WebElement> actualRevenuePercent = driver
                    .findElements(By.xpath("(//div[@class='_widgetWrapper_100me_37'])[2]/div"));
            actualRevenuePercent.add(0, driver.findElement(By.xpath("//p[text()='Company Expense']")));
            for (int i = 1; i < actualRevenuePercent.size(); i++) {
                String[] actualRevenuePercentageText = driver
                        .findElement(By.xpath("(//div[@class='_widgetWrapper_100me_37'])[2]/div["+i+"]//span[@style]")).getText()
                        .split("%");
                actualRevenuePercentage = actualRevenuePercentageText[0];
                Actions act = new Actions(driver);
                act.moveToElement(
                                driver.findElement(By.xpath("(//div[@class='_widgetWrapper_100me_37'])[2]/div["+i+"]//span[@style]")))
                        .perform();
                String toolTipData = driver.findElement(By.xpath("(//div[@class='_widgetWrapper_100me_37'])[2]/div["+i+"]//div[@class='_timeWrapper_100me_68']/div[3]")).getText();
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
                }
                else if (df.format(revenuePercent).equals("∞")) {
                    System.out.println("0 %");
                }
                else {
                    System.out.println(false);
                }
            }
        }
    }

    public void verifyActiveProjectsPercentagePerMonth() {
        driver.navigate().refresh();
//				if (driver.findElement(noDataBillabilityMetrics).isDisplayed()) {
//					System.out.println(driver.findElement(noDataBillabilityMetrics).getText());
//				} else {
        String actualRevenuePercentage = null;
        List<WebElement> actualRevenuePercent = driver
                .findElements(By.xpath("//div[@class='_widgetWrapper_1v5iq_13']/div"));
        actualRevenuePercent.add(0, driver.findElement(By.xpath("//p[text()='Active Projects']")));
        for (int i = 1; i < actualRevenuePercent.size(); i++) {
            String[] actualRevenuePercentageText = driver
                    .findElement(By.xpath("//div[@class='_widgetWrapper_1v5iq_13']/div["+ i +"]//div[@style]")).getText()
                    .split("%");
            actualRevenuePercentage = actualRevenuePercentageText[0];
//						System.out.println(actualRevenuePercentage);
            Actions act = new Actions(driver);
            act.moveToElement(
                            driver.findElement(By.xpath("//div[@class='_widgetWrapper_1v5iq_13']/div["+ i +"]//div[@style]")))
                    .perform();
            String toolTipData = driver.findElement(By.xpath("//div[@class='_widgetWrapper_1v5iq_13']/div["+i+"]//div[@class='_chipContainer_1v5iq_58']/div[2]")).getText();
            System.out.println(toolTipData);

            String[] tooltipValues = toolTipData.split(":");

            String getCurrentrevenue = tooltipValues[1];
            String[] Currentrevenue = getCurrentrevenue.split("Vs last month");
            String actualCurrentValue = Currentrevenue[0].trim();
            System.out.println(actualCurrentValue);
            String LastMonthRevenue = tooltipValues[2].trim();
            currentRevenue = Long.parseLong(actualCurrentValue);
            lastMonthRevenue = Long.parseLong(LastMonthRevenue);

            DecimalFormat df = new DecimalFormat("0.00");

            long differenceRevenue = currentRevenue - lastMonthRevenue;

            System.out.println(differenceRevenue);

            double revenuePercent = Math.floor(differenceRevenue * 100) / lastMonthRevenue;

//						df.setRoundingMode(RoundingMode.DOWN);
//						System.out.println("\ndouble (RoundingMode.DOWN) : " + df.format(revenuePercent));

            df.setRoundingMode(RoundingMode.UP);
            System.out.println("double (RoundingMode.UP)  : " + df.format(revenuePercent));

            if (df.format(revenuePercent).contains(actualRevenuePercentage)) {
                System.out.println(true);
            }
            else if (df.format(revenuePercent).equals("∞")) {
                System.out.println("0 %");
            }
            else {
                System.out.println(false);
            }
        }
    }

//				}


    public void verifyEmployeesPercentagePerMonth() {
        driver.navigate().refresh();
//				if (driver.findElement(noDataBillabilityMetrics).isDisplayed()) {
//					System.out.println(driver.findElement(noDataBillabilityMetrics).getText());
//				} else {
        String actualRevenuePercentage = null;
        List<WebElement> actualRevenuePercent = driver
                .findElements(By.xpath("//div[@class='_widgetWrapper_15myj_13']/div"));
        actualRevenuePercent.add(0, driver.findElement(By.xpath("//p[text()='Employees']")));
        for (int i = 1; i < actualRevenuePercent.size(); i++) {
            String[] actualRevenuePercentageText = driver
                    .findElement(By.xpath("//div[@class='_widgetWrapper_15myj_13']/div["+i+"]//span[@style]")).getText()
                    .split("%");
            actualRevenuePercentage = actualRevenuePercentageText[0];
//						System.out.println(actualRevenuePercentage);
            Actions act = new Actions(driver);
            act.moveToElement(
                            driver.findElement(By.xpath("//div[@class='_widgetWrapper_15myj_13']/div["+i+"]//span[@style]")))
                    .perform();
            String toolTipData = driver.findElement(By.xpath("//div[@class='_widgetWrapper_15myj_13']/div["+i+"]//div[@class='_chipContainer_15myj_83']/div[2]")).getText();
            System.out.println(toolTipData);

            String[] tooltipValues = toolTipData.split(":");

            String getCurrentrevenue = tooltipValues[1];
            String[] Currentrevenue = getCurrentrevenue.split("Vs last month");
            String actualCurrentValue = Currentrevenue[0].trim();
            System.out.println(actualCurrentValue);
            String LastMonthRevenue = tooltipValues[2].trim();
            currentRevenue = Long.parseLong(actualCurrentValue);
            lastMonthRevenue = Long.parseLong(LastMonthRevenue);

            DecimalFormat df = new DecimalFormat("0.00");

            long differenceRevenue = currentRevenue - lastMonthRevenue;

            System.out.println(differenceRevenue);

            double revenuePercent = Math.floor(differenceRevenue * 100) / lastMonthRevenue;

//						df.setRoundingMode(RoundingMode.DOWN);
//						System.out.println("\ndouble (RoundingMode.DOWN) : " + df.format(revenuePercent));

            df.setRoundingMode(RoundingMode.UP);
            System.out.println("double (RoundingMode.UP)  : " + df.format(revenuePercent));

            if (df.format(revenuePercent).contains(actualRevenuePercentage)) {
                System.out.println(true);
            }
            else if (df.format(revenuePercent).equals("∞")) {
                System.out.println("0 %");
            }
            else {
                System.out.println(false);
            }
        }
    }
//			}


    public void verifyBenchPercentagePerMonth() {
        driver.navigate().refresh();
//				if (driver.findElement(noDataBillabilityMetrics).isDisplayed()) {
//					System.out.println(driver.findElement(noDataBillabilityMetrics).getText());
//				} else {
        String actualRevenuePercentage = null;

        List<WebElement> actualRevenuePercent = driver
                .findElements(By.xpath("//div[@class='_widgetWrapper_10b12_52']/div"));
        actualRevenuePercent.add(0, driver.findElement(By.xpath("//p[text()='Bench']")));
        for (int i = 1; i < actualRevenuePercent.size(); i++) {
            String[] actualRevenuePercentageText = driver
                    .findElement(By.xpath("(//div[@class='_widgetWrapper_10b12_52']//div[@data-tooltip-id])[\"+i+\"]/div[1]/div[@style]")).getText()
                    .split("%");
            actualRevenuePercentage = actualRevenuePercentageText[0];
            Actions act = new Actions(driver);
            act.moveToElement(
                            driver.findElement(By.xpath("(//div[@class='_widgetWrapper_10b12_52']//div[@data-tooltip-id])[\"+i+\"]/div[1]/div[@style]")))
                    .perform();
            String toolTipData = driver.findElement(By.xpath("(//div[@class='_widgetWrapper_10b12_52']//div[@data-tooltip-id])[\"+ i +\"]/parent::div/div[2]")).getText();
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
            }
            else if (df.format(revenuePercent).equals("∞")) {
                System.out.println("0 %");
            }
            else {
                System.out.println(false);
            }
        }
    }
    public void infoIconMsg( String infoMsg)
    {
      genericUtils.waitUntilElementVisibiltity(welcomeMsg);
      genericUtils.clickButton(infoIcon);
    }

    public void goToWidgetAndCalculateTotalrevenue()
    {
        customizeWidgetBtn();
        genericUtils.clickButton(enableBtn);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        genericUtils.clickButton(closeCustomizeWidget);
        if (allRevenue>allexpenses)
        {  profit=allRevenue-allexpenses;
            System.out.println("profit indicates in green color"+profit);
        }
        else {
            loss=allexpenses-allRevenue;
            System.out.println("loss indicates in red color"+loss);
        }

    }


}
