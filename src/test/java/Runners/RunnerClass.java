package Runners;

import Utils.CommonMethods;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/Resources/Features/",
        glue = "Steps",
        dryRun = false,
        tags = "",
        plugin = {"pretty","html:target/cucumber.html","json:target/cucumber.json","rerun:target/failed.txt"}
)


public class RunnerClass extends CommonMethods {
}
