package test.resources.jsonschema;

public class SchemaPath {


    public static String selector(int schema) {

        String path = "";
        switch (schema){
            case 1:
                path = "src/test/resources/jsonschema/CreateIssueSchema.json";
                break;
            case 2:
                path = "src/test/resources/jsonschema/CreateCommentSchema.json";
                break;
        }
        return path;
    }
}
