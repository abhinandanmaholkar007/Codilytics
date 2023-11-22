package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.GenericUtils;
import java.util.List;

public class BenchPage {
	
	public WebDriver driver;
	public GenericUtils genericUtil;
	
	public BenchPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.genericUtil = new GenericUtils(driver);
	}


	@FindBy(xpath = "//p[text()='Bench']")
			WebElement benchTab;

	By benchDetailsCards = By.xpath("//div[contains(@class,'overAllContainer')]/div[1]//div[contains(@class,'mainTitle')]");

	@FindBy(id ="currentStateContainerId" )
			WebElement benchFilterField;

	@FindBy(xpath = "//div[contains(@class,'selectedOption')]")
			WebElement SelectedBenchFilter;

	By benchFiltersOptions = By.xpath("//div[contains(@class,'optionsContent')]//p");
	
	public void tapOnBench()
	{
		genericUtil.clickButton(benchTab);
	}
	
	public void verifyBenchPage(String expectedUrl)
	{
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, expectedUrl);
	}
	
	public void verfiyBenchDetailsCard(List<String> expectedBenchCard)
	{
		List<WebElement> actualBenchCardsList = driver.findElements(benchDetailsCards);
		
		for (WebElement actualBenchCards : actualBenchCardsList) {
			System.out.println(actualBenchCards.getText());
			int k=0;
			for (int j = k ; j < expectedBenchCard.size(); j++) {
				
				if (expectedBenchCard.get(j).equalsIgnoreCase(actualBenchCards.getText())) {
					System.out.println(expectedBenchCard.get(j));
					System.out.println(true);
					k++;
					break;
					
				}
				
					
				}
			System.out.println("-------------");
			}
			
		}
	
	public void verifyBenchFilterField()
	{
		benchFilterField.isDisplayed();
		genericUtil.clickButton(benchFilterField);
	}
	
	public void verifySelectedBenchFilters()
	{
		SelectedBenchFilter.isSelected();
	}
	
	public void verifyBenchFilterOptions(List<String> expectedBenchFilters)
	{
		List<WebElement> actualBenchFilterList = driver.findElements(benchFiltersOptions);
		for (WebElement actualBenchFilter : actualBenchFilterList) {
			System.out.println(actualBenchFilter.getText());
			int k=0;
			for (int j = k ; j < expectedBenchFilters.size(); j++) {
				
				if (expectedBenchFilters.get(j).equalsIgnoreCase(actualBenchFilter.getText())) {
					System.out.println(expectedBenchFilters.get(j));
					System.out.println(true);
					k++;
					break;
					
				}
				
					
				}
			System.out.println("-------------");
			}
		
	}
	}


