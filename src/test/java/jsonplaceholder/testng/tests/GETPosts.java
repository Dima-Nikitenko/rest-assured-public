package jsonplaceholder.testng.tests;

import io.qameta.allure.Step;
import jsonplaceholder.framework.BaseTest;
import jsonplaceholder.constants.EndPoints;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;

public class GETPosts extends BaseTest {

    @Test(description = "GET All Posts")
    @Step("GET-ting all posts...")
    public void getListOfAllPosts() {
        response =
                given()
                        .basePath(EndPoints.posts)
                .when()
                        .get();
        assertStatusCode(200);
        assertContentType(JSON);
        assertResponseObjectListSortedAscendingByKey("id");
        softAssert.assertAll();
    }
}
