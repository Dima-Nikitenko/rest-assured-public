package jsonplaceholder.framework;

import io.qameta.allure.Step;
import jsonplaceholder.constants.EndPoints;
import jsonplaceholder.constants.Specifications;
import jsonplaceholder.framework.responsehandling.ResponseBody;
import org.testng.annotations.BeforeSuite;

public class BaseTest extends ResponseBody {

    @BeforeSuite
    @Step("Setting endpoints and specifications...")
    public void setup() {
        EndPoints.setGlobalConstants();
        Specifications.setSpecifications();
    }
}
