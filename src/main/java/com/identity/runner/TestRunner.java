package com.identity.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = {"com/identity/steps"}, tags = "@E2E",
        plugin = {"pretty", "html:target/report/cucumber.html",
                "json:target/report/cucumber.json"},
        monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

}
