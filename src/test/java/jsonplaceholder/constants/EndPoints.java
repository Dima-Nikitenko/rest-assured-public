package jsonplaceholder.constants;

import io.restassured.RestAssured;
import jsonplaceholder.framework.PropertiesHandler;

public class EndPoints {

    public static PropertiesHandler property;

    private static final String PROPERTIES_FILE = "endpoints.properties";
    private static final String BASE_URI = "baseURI";
    private static final String PATH_TO_POSTS = "posts";
    private static final String PATH_TO_USERS = "users";

    public static String baseUri;
    public static String posts;
    public static String users;

    public static void setGlobalConstants() {
        property = new PropertiesHandler(PROPERTIES_FILE);
        baseUri = property.getProperty(BASE_URI);
        posts = property.getProperty(PATH_TO_POSTS);
        users = property.getProperty(PATH_TO_USERS);
        RestAssured.baseURI = baseUri;
    }
}
