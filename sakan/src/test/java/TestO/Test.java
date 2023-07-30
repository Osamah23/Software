package TestO;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"TestO"},
        features = "src/test/resources/use_cases")

public class Test {

}
