import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(dryRun = true,
        monochrome = true,
        features = "src/test/java/features/",
        tags = "@CucumberTest",
        glue = "stepdef",
        plugin = {"pretty", "html:target/reports.html"})
public class CucumberDriver {
}
