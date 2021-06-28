package test.java.co.com.jira.utils;

import io.restassured.path.json.JsonPath;
import org.json.JSONObject;

public class JsonHandler {

    public static JsonPath rawToJson(String response){

        return new JsonPath(response);
    }

    public static JSONObject rawToJsonObject(String response){

        return new JSONObject(response);
    }
}
