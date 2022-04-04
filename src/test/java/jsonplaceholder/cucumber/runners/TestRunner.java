package jsonplaceholder.cucumber.runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/java/jsonplaceholder/cucumber/features",
        glue= { "jsonplaceholder.cucumber.stepdefinitions", "jsonplaceholder.cucumber.hooks" },
        plugin = { "html:test-reports/cucumber-reports/cucumber.html",
                   "json:test-reports/cucumber-reports/cucumber.json",
                   "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" })

public class TestRunner extends AbstractTestNGCucumberTests {}
