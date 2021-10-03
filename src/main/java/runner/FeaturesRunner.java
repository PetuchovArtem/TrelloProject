package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        tags = "@Smoke",
//        plugin = {"json:target/cucumber-report.json", "pretty", "com.epam.reportportal.cucumber.ScenarioReporter"},
          glue = "steps"
)
public class FeaturesRunner {
    private FeaturesRunner(){}
}
