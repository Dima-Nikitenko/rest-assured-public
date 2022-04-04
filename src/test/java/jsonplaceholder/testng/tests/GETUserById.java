package jsonplaceholder.testng.tests;

import io.qameta.allure.Step;
import jsonplaceholder.framework.BaseTest;
import jsonplaceholder.constants.EndPoints;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import static io.restassured.RestAssured.*;

public class GETUserById extends BaseTest {

    @Parameters( "UserId" )
    @Test(description = "GET User By ID")
    @Step("GET-ting user by [id = {userId}]...")
    public void getUserById(String userId) {
        response =
                given()
                        .pathParam("id", userId)
                        .basePath(EndPoints.users)
                .when()
                        .get("/{id}");
        assertStatusCode(200);
        assertResponseObjectEqualsObjectTakenFromFile("sampleUser");
        softAssert.assertAll();
    }
}
