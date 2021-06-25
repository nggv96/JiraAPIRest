package test.resources.payloads;

public class JiraPayloads {

     public static String authentication(String user, String pss){


         String payload = "{ \"username\": \""+user+"\", \"password\": \""+pss+"\" }";

         return payload;
     }

     public static String addComment(String comment){

         String payload = "{\n" +
                 "    \"body\": \""+comment+"\",\n" +
                 "    \"visibility\": {\n" +
                 "        \"type\": \"role\",\n" +
                 "        \"value\": \"Administrators\"\n" +
                 "    }\n" +
                 "}";

         return payload;
     }

     public static String createIssue(String projectKey, String summary, String description, String issuetype){

         String payload = "{\n" +
                 "\t   \"fields\": {\n" +
                 "\t\t   \"project\":{\n" +
                 "\t\t\t\t\"key\": \""+projectKey+"\"\n" +
                 "\t\t   },\n" +
                 "\t\t   \"summary\": \""+summary+"\",\n" +
                 "\t\t   \"description\": \""+description+"\",\n" +
                 "\t\t   \"issuetype\": {\n" +
                 "\t\t\t   \"name\": \""+issuetype+"\"\n" +
                 "\t\t   }\n" +
                 "\t   }\n" +
                 "   }";

         return payload;
     }
}
