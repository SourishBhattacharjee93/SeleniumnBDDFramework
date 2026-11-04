package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"classpath:features"},
        glue = {"stepDefinitions","Hooks"},
        dryRun = false,
        monochrome = true,
        tags = "@Scenario1",
        plugin = {"pretty", "html:target/cucumber.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios(ITestContext context) {
        return super.scenarios(context);
    }
}