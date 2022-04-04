package jsonplaceholder.testng.tests;

import io.qameta.allure.Step;
import jsonplaceholder.framework.BaseTest;
import jsonplaceholder.constants.EndPoints;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;

public class GETUsers extends BaseTest {

    @Test(description = "GET All Users")
    @Step("GET-ting all users...")
    public void getAllUsers() {
        response =
                given()
                        .basePath(EndPoints.users)
                .when()
                        .get();
        assertStatusCode(200);
        assertContentType(JSON);
        assertResponseBodyContainsObjectFromFile("sampleUser", "id", 5);
        softAssert.assertAll();
    }
}
