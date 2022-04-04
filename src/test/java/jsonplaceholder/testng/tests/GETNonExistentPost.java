package jsonplaceholder.testng.tests;

import io.qameta.allure.Step;
import jsonplaceholder.constants.EndPoints;
import jsonplaceholder.framework.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GETNonExistentPost extends BaseTest {


    @Parameters("PostId")
    @Test(description = "GET Non-Existent Post By Its ID")
    @Step("GET-ting post by non-existent [id = {id}]...")
    public void emptyBodyForNonExistentPost(int id) {
        response =
                given()
                        .pathParam("id", id)
                        .basePath(EndPoints.posts)
                .when()
                        .get("/{id}");
        assertStatusCode(404);
        assertResponseBodyEmpty();
        softAssert.assertAll();
    }
}
