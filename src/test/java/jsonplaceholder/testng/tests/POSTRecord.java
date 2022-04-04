package jsonplaceholder.testng.tests;

import io.qameta.allure.Step;
import jsonplaceholder.constants.EndPoints;
import jsonplaceholder.framework.BaseTest;
import jsonplaceholder.framework.utils.FileUtils;
import jsonplaceholder.framework.utils.RandomUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class POSTRecord extends BaseTest {

    private JSONObject sentObject;
    private final HashMap<Object, Object> keyValue = new HashMap<>();

    @Test(description = "POST New Post With A Certain Body")
    @Step("POST-ing request with [title, body, userId]...")
    public void createRecord() {
        keyValue.put("title", RandomUtils.generateRandomAlphanumericString(5));
        keyValue.put("body", RandomUtils.generateRandomAlphanumericString(5));
        keyValue.put("userId", 1);
        FileUtils.writeToJSONFile("sampleObj.json", keyValue);
        sentObject = new JSONObject(FileUtils.readJSONFile("sampleObj.json"));
        response =
                given()
                        .basePath(EndPoints.posts)
                        .body(sentObject.toString())
                .when()
                        .post();
        assertStatusCode(201);
        assertCreatedObjectKeysEqualsPostedKeys(sentObject, "title", "body", "userId");
        assertPostedObjectHasKeys("id");
        softAssert.assertAll();
    }
}
