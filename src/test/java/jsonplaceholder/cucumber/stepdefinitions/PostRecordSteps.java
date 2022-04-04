package jsonplaceholder.cucumber.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jsonplaceholder.constants.EndPoints;
import jsonplaceholder.framework.BaseTest;
import jsonplaceholder.framework.utils.FileUtils;
import jsonplaceholder.framework.utils.RandomUtils;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostRecordSteps extends BaseTest {

    private JSONObject sentObject;
    private final HashMap<Object, Object> keyValue = new HashMap<>();

    @When("I post a new post record")
    public void iPostANewPostRecord() {
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
    }

    @Then("POST record response status code is {int}")
    public void statusCodeIs(int statusCode) {
        assertStatusCode(statusCode);
    }

    @And("Following POST record response keys corresponds to the ones passed in the request")
    public void keysCorrespondsToTheOnesPassedInTheRequest(DataTable keys) {
        List<String> keysList = keys.asList();
        for(String key: keysList) assertCreatedObjectKeysEqualsPostedKeys(sentObject, key);
    }

    @And("Following POST record response keys are present in the response")
    public void keyIsPresentInTheResponse(DataTable keys) {
        List<String> keysList = keys.asList();
        for(String key: keysList) assertPostedObjectHasKeys(key);
        softAssert.assertAll();
    }
}
