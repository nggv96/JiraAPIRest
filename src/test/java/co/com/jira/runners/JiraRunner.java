package test.java.co.com.jira.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/jira.feature"
        ,glue = "test.java.co.com.jira.stepdefinitions"
        ,snippets = CucumberOptions.SnippetType.CAMELCASE
        //,tags = "@DeleteIssue"
)

public class JiraRunner {
}
