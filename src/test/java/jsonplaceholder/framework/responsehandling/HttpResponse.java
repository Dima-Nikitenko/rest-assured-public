package jsonplaceholder.framework.responsehandling;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HttpResponse {

    public Response response;

    @Step("Asserting status code = {expectedCode}...")
    public void assertStatusCode(int expectedCode) {
        response.then()
                .statusCode(expectedCode);
    }

    @Step("Asserting Content-Type code equals {expectedContentType}...")
    public void assertContentType(ContentType expectedContentType) {
        response.then()
                .contentType(expectedContentType);
    }

    public void extractResponse() {
        response.then()
                .extract().response();
    }

    public String extractResponseAsString() {
        response.then()
                .extract().response();
        return response.asString();
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
