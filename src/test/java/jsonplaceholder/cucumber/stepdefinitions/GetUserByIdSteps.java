package jsonplaceholder.cucumber.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jsonplaceholder.constants.EndPoints;
import jsonplaceholder.framework.BaseTest;

import static io.restassured.RestAssured.given;

public class GetUserByIdSteps extends BaseTest {

    @When("I retrieve user with id = {int}")
    public void iRetrieveUserWithId(int userId) {
        response =
                given()
                        .pathParam("id", userId)
                        .basePath(EndPoints.users)
                .when()
                        .get("/{id}");
    }

    @Then("GET user by id response status code is {int}")
    public void statusCodeIs(int statusCode) {
        assertStatusCode(statusCode);
    }

    @And("Response user has data like in {string} file")
    public void responseUserHasDataLikeInFile(String fileName) {
        assertResponseObjectEqualsObjectTakenFromFile(fileName);
        softAssert.assertAll();
    }
}
