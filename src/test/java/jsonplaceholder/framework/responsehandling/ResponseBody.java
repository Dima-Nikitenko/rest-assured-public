package jsonplaceholder.framework.responsehandling;

import io.qameta.allure.Step;
import jsonplaceholder.framework.utils.CompareUtils;
import jsonplaceholder.framework.utils.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.ArrayUtils.isSorted;

public class ResponseBody extends HttpResponse {

    public JSONArray responseArray;
    public JSONObject responseObject;

    public SoftAssert softAssert = new SoftAssert();

    @Step("Asserting response body like expected...")
    public void assertResponseObjectEqualsObjectTakenFromFile(String fileName) {
        JSONObject actualObject = new JSONObject(response.getBody().asString());
        JSONObject expectedObject = new JSONObject(FileUtils.readJSONFile(fileName));
        softAssert.assertTrue(CompareUtils.compareTwoJsonObjects(expectedObject, actualObject));
    }

    @Step("Asserting response Key values like expected...")
    public void assertResponseObjectKeyValuesEqualExpected(Map<String, ?> keyValues) {
        responseObject = new JSONObject(extractResponseAsString());
        keyValues.forEach((key, expectedValue) -> softAssert.assertEquals(responseObject.get(key), expectedValue));
    }

    @Step("Asserting response Key value not empty...")
    public void assertResponseObjectKeyValueNotEmpty(String ... keys) {
        responseObject = new JSONObject(extractResponseAsString());
        for (String key: keys) softAssert.assertFalse(responseObject.getString(key).isEmpty());
    }

    @Step("Asserting response object sorted ASC by Key...")
    public void assertResponseObjectListSortedAscendingByKey(String key) {
        JSONArray jsonArray = new JSONArray(extractResponseAsString());
        int[] arr = new int[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            arr[i] = jsonObject.getInt(key);
        }
        softAssert.assertTrue(isSorted(arr));
    }

    @Step("Asserting response body contains object with {key} = {value}...")
    public void assertResponseBodyContainsObjectFromFile(String fileName, String key, int value) {
        JSONObject expectedObject = new JSONObject(FileUtils.readJSONFile(fileName));
        JSONObject actualObject = null;
        JSONArray jsonArray = new JSONArray(response.getBody().asString());
        for (int i = 0; i < jsonArray.length(); i++) {
            actualObject = jsonArray.getJSONObject(i);
            if (actualObject.getInt(key) == value) break;
        }
        softAssert.assertTrue(CompareUtils.compareTwoJsonObjects(expectedObject, actualObject));
    }

    @Step("Asserting posted object has key...")
    public void assertPostedObjectHasKeys(String ... keys) {
        JSONObject actualObject = new JSONObject(response.asString());
        for (String key: keys) softAssert.assertTrue(actualObject.has(key));
    }

    @Step("Asserting created object Key equals posted Keys...")
    public void assertCreatedObjectKeysEqualsPostedKeys(JSONObject sentObject, String ... keys) {
        JSONObject actualObject = new JSONObject(response.asString());
        for (String key: keys) softAssert.assertEquals(sentObject.get(key), actualObject.get(key));
    }

    @Step("Asserting response body empty...")
    public void assertResponseBodyEmpty() {
        JSONObject emptyBody = new JSONObject(extractResponseAsString());
        softAssert.assertTrue(emptyBody.isEmpty());
    }
}
