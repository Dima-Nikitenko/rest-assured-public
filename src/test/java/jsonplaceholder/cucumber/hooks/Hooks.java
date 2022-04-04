package jsonplaceholder.cucumber.hooks;

import io.cucumber.java.*;
import static jsonplaceholder.constants.EndPoints.setGlobalConstants;
import static jsonplaceholder.constants.Specifications.setSpecifications;
import static jsonplaceholder.framework.utils.FileUtils.removeAllureResultFileContainingString;

public class Hooks {

    @BeforeAll
    public static void setup() {
        setGlobalConstants();
        setSpecifications();
    }

    @AfterAll
    public static void teardown() {
        removeAllureResultFileContainingString("io.cucumber.testng.AbstractTestNGCucumberTests.runScenario");
    }
}
