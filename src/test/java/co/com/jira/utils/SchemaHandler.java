package test.java.co.com.jira.utils;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SchemaHandler {

    public static Schema defineSchema(String schemaPath) throws FileNotFoundException {

        File schemaFile = new File(schemaPath);
        System.out.println("Path: "+schemaPath);
        JSONTokener schemaData = new JSONTokener(new FileInputStream(schemaFile));
        JSONObject jsonSchema = new JSONObject(schemaData);

        Schema schemaValidator = SchemaLoader.load(jsonSchema);

        return schemaValidator;
    }
}
