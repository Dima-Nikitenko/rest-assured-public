package jsonplaceholder.constants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.builder.RequestSpecBuilder;

public class Specifications extends EndPoints{

    public static void setSpecifications() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }
}
