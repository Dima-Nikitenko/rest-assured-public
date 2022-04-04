package jsonplaceholder.cucumber.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import jsonplaceholder.constants.EndPoints;
import jsonplaceholder.framework.BaseTest;

import static io.restassured.RestAssured.given;

public class GetPostsSteps extends BaseTest {

    @When("I retrieve all posts")
    public void method1() {
        response =
                given()
                        .basePath(EndPoints.posts)
                .when()
                        .get();
    }

    @Then("GET posts response status code is {int}")
    public void statusCodeIs(int statusCode) {
        assertStatusCode(statusCode);
    }

    @And("GET posts response Content-Type is {string}")
    public void contentTypeIs(String contentType) {
        assertContentType(ContentType.valueOf(contentType));
    }

    @And("Response list is sorted ASC by {string}")
    public void responseListIsSortedASCBy(String key) {
        assertResponseObjectListSortedAscendingByKey(key);
        softAssert.assertAll();
    }
}
