package jsonplaceholder.cucumber.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import jsonplaceholder.constants.EndPoints;
import jsonplaceholder.framework.BaseTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetPostByIdSteps extends BaseTest {

    @When("I retrieve post with id = {int}")
    public void iRetrievePostWithId(int postId) {
        response =
                given()
                        .pathParam("id", postId)
                        .basePath(EndPoints.posts)
                .when()
                        .get("/{id}");
    }

    @Then("GET post by id response status code is {int}")
    public void statusCodeIs(int statusCode) {
        assertStatusCode(statusCode);
    }

    @And("GET post by id response keys below have the following values")
    public void keysAreLikeExpected(Map<String, Integer> keyValues) {
        assertResponseObjectKeyValuesEqualExpected(keyValues);
    }

    @And("Following GET post by id response keys are not empty strings")
    public void keysAreNotEmptyStrings(DataTable keys) {
        List<String> keysList = keys.asList();
        for (String key: keysList) assertResponseObjectKeyValueNotEmpty(key);
        softAssert.assertAll();
    }
}
