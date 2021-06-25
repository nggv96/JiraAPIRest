package test.resources.apiResources;

public class JiraResources {

    public static String authentication(){

        return "/rest/auth/1/session";
    }

    public static String createIssue(){

        return "/rest/api/2/issue";
    }

    public static String addComment(){

        return "rest/api/2/issue/{key}/comment";
    }

    public static String addAttachment(){

        return "/rest/api/2/issue/{key}/attachments";
    }

    public static String getIssue(){

        return "/rest/api/2/issue/{key}";
    }
}
