package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"classpath:featuresAPI"},
        glue = {"stepDefinitionsAPI"},
        dryRun = false,
        monochrome = false,
        tags = "@AddPlaceScenario",
        plugin = {"pretty", "html:target/cucumber.html"}
)
public class TestRunnerAPI extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios(ITestContext context) {
        return super.scenarios(context);
    }
}