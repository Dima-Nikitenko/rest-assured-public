package jsonplaceholder.framework.utils;

import io.qameta.allure.Step;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CompareUtils {

    static ObjectMapper mapper = new ObjectMapper();

    @Step("Comparing two JSON objects...")
    public static boolean compareTwoJsonObjects(JSONObject firstObject, JSONObject secondObject) {
        try {
            if (mapper.readTree(String.valueOf(firstObject)).equals(mapper.readTree(String.valueOf(secondObject)))) return true;
        } catch (JsonProcessingException exc) {
            exc.printStackTrace();
        }
        return false;
    }
}
