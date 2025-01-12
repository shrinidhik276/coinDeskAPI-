package com.mystore.TestRunner;

//import com.mystore.RetryFailedCases.Retry;
import com.mystore.RetryFailedCases.AnnotationTransformer;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
	    features = "src/test/resources/com/mystore/feature",  // Path to the feature files
	    glue = {"com.mystore.steps", "com.mystore.hooks"},  // Path to the step definition files
	    plugin = {
	        "pretty", // Display a readable format of the test output
	        "html:target/cucumber-reports/html", // HTML report location
	        "json:target/cucumber-reports/cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",// JSON report location
				"rerun:target/rerun.txt"
	    },
	    tags = "@cart"  // Optional: Run tests with the smoke tag (you can also remove this if no tags are used)
	)

	public class CucumberRunnerIT extends AbstractTestNGCucumberTests {

	@Override

	@DataProvider(parallel = false)
//	@Test(retryAnalyzer = RetryAnalyzer.class)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
