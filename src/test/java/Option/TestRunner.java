package Option;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features ="src/feature",
        glue = {"stepdefination"},
        plugin = "json:target/jsonReport/Cucumber-report",
        tags = {"@AddPlace"}
)

public class TestRunner {

}
