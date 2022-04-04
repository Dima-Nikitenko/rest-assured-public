package jsonplaceholder.cucumber.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import jsonplaceholder.constants.EndPoints;
import jsonplaceholder.framework.BaseTest;

import static io.restassured.RestAssured.given;

public class GetUsersSteps extends BaseTest {

    @When("I retrieve all users")
    public void iRetrieveAllUsers() {
        response =
                given()
                        .basePath(EndPoints.users)
                .when()
                        .get();
    }

    @Then("GET users response status code is {int}")
    public void statusCodeIsStatusCode(int statusCode) {
        assertStatusCode(statusCode);
    }

    @And("GET users response Content-Type is {string}")
    public void contentTypeIsContentType(String contentType) {
        assertContentType(ContentType.valueOf(contentType));
    }

    @And("User with {string} = {int} has data like in {string} file")
    public void userWithHasDataLikeInFile(String key, int value, String fileName) {
        assertResponseBodyContainsObjectFromFile(fileName, key, value);
        softAssert.assertAll();
    }
}
