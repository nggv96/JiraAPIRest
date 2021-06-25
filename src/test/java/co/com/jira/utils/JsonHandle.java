package test.java.co.com.jira.utils;

import io.restassured.path.json.JsonPath;

public class JsonHandle {

    public static JsonPath rawToJson(String response){

        JsonPath js = new JsonPath(response);
        return js;
    }
}
