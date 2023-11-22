package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BenchPage;
import utils.TestContextSetUp;

import java.util.List;


public class BenchStepDefinition {

	public BenchPage benchPage;
	public TestContextSetUp testContextSetup;
	
	public BenchStepDefinition(TestContextSetUp testContextSetup) {
		this.testContextSetup=testContextSetup;
		this.benchPage=testContextSetup.pageObjectManager.getBenchPage();
	}
	
	@When("user tap on bench tab from side bar")
	public void user_tap_on_bench_tab_from_side_bar() {

		benchPage.tapOnBench();
	}
	

@Then("User should be navigated to bench page as {string}")
public void user_should_be_navigated_to_bench_page_as(String expectedUrl) {
	benchPage.verifyBenchPage(expectedUrl);
}

@Then("verify that bench details cards should be displayed")
public void verify_that_bench_details_cards_should_be_displayed(List<String> benchCards) {
	benchPage.verfiyBenchDetailsCard(benchCards);
    
}

@When("user tap on the bench filter bar")
public void user_tap_on_the_bench_filter_bar() {
    benchPage.verifyBenchFilterField();
}

@Then("verify that Time month option is selected")
public void verify_that_time_month_option_is_selected() {
    benchPage.verifySelectedBenchFilters();
}

@Then("verify that filter options")
public void verify_that_filter_options(List<String> expectedBenchFilters) {
	benchPage.verifyBenchFilterOptions(expectedBenchFilters);
}

}
