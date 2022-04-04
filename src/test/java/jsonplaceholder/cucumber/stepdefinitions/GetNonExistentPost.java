package jsonplaceholder.cucumber.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jsonplaceholder.constants.EndPoints;
import jsonplaceholder.framework.BaseTest;

import static io.restassured.RestAssured.given;

public class GetNonExistentPost extends BaseTest {

    @When("I retrieve post with non-existent id = {int}")
    public void iRetrieveNonExistentPostWithId(int postId) {
        response =
                given()
                        .pathParam("id", postId)
                        .basePath(EndPoints.posts)
                .when()
                        .get("/{id}");
    }

    @Then("Status code is {int}")
    public void statusCodeIs(int statusCode) {
        assertStatusCode(statusCode);
    }

    @And("Response body is empty")
    public void responseBodyIsEmpty() {
        assertResponseBodyEmpty();
        softAssert.assertAll();
    }
}
