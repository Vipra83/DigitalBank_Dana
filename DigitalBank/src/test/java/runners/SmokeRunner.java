package runners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "html:target/default-cucumber-reports",
                "json:target/cucumber.json"},
        features = {"classpath:features"},
        glue = {"step_defs"},
        //tags = {"~@ignore", "@smoke"},
        //tags = {"@LoginFlow"},
        dryRun = false
)

public class SmokeRunner {

}
