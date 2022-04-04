package jsonplaceholder.testng.tests;

import io.qameta.allure.Step;
import jsonplaceholder.framework.BaseTest;
import jsonplaceholder.constants.EndPoints;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class GETPostById extends BaseTest {

    HashMap<String, Object> expectedKeyValues = new HashMap<>();

    @Parameters("PostId")
    @Test(description = "GET Post By Its ID")
    @Step("GET-ting post by [id = {postId}]...")
    public void getPostById(int postId) {
        response =
                given()
                        .pathParam("id", postId)
                        .basePath(EndPoints.posts)
                .when()
                        .get("/{id}");
        assertStatusCode(200);
        expectedKeyValues.put("userId", 10);
        expectedKeyValues.put("id", postId);
        assertResponseObjectKeyValuesEqualExpected(expectedKeyValues);
        assertResponseObjectKeyValueNotEmpty("title", "body");
        softAssert.assertAll();
    }
}
