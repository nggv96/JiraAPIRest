package test.java.co.com.jira.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;
import test.java.co.com.jira.utils.DataSaver;
import test.java.co.com.jira.utils.JsonHandler;
import test.java.co.com.jira.utils.SchemaHandler;
import test.resources.apiResources.JiraResources;
import test.resources.payloads.JiraPayloads;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static io.restassured.RestAssured.*;



public class JiraStepDefinition {

    static DataSaver data = new DataSaver();

    SessionFilter session = new SessionFilter();
    Response response;

    @Before
    public void beforeJira(){
            RestAssured.baseURI = "http://localhost:8080";
    }

    @Given("authentication witn user {string} and password {string}")
    public void authenticationWitnUserAndPassword(String user, String pss) {
        given().log().all().header("Content-Type", "application/json")
                .body(JiraPayloads.authentication(user, pss)).filter(session)
                .when().post(JiraResources.authentication())
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
    }
    @When("user calls CreateIssue API with POST http request with summary {string}")
    public void userCallsCreateIssueAPIWithPOSTHttpRequestWithSummary(String summary) {
        response = given().log().all().header("Content-Type","application/json")
                .body(JiraPayloads.createIssue("RSA",summary,"Aqui van los criterios","Story")).filter(session)
                .when().post(JiraResources.createIssue())
                .then().log().all().assertThat().statusCode(201)
                .extract().response();

        JsonPath responseJs = JsonHandler.rawToJson(response.asString());
        data.setIssueKey(responseJs.getString("key"));
    }
    @Then("the API call is success with status code {int}")
    public void theAPICallIsSuccessWithStatusCode(int status) throws FileNotFoundException {

        Assert.assertEquals(status, response.getStatusCode());

        JSONObject jsonResponse = JsonHandler.rawToJsonObject(response.body().asString());

        Schema schemaValidator = SchemaHandler.defineSchema("src/test/resources/jsonschema/CreateIssueSchema.json");

        schemaValidator.validate(jsonResponse);


    }

    @When("user calls CreateComment API with POST http request with comment {string}")
    public void userCallsCreateCommentAPIWithPOSTHttpRequestWithComment(String comment) {
        response = given().pathParam("key",data.getIssueKey()).log().all()
                .header("Content-Type", "application/json")
                .body(JiraPayloads.addComment(comment)).filter(session)
                .when().post(JiraResources.addComment())
                .then().log().all().assertThat().statusCode(201)
                .extract().response();

        JsonPath responseJs = JsonHandler.rawToJson(response.asString());
        data.setIdCom(responseJs.getString("id"));
    }

    @When("user calls DeleteComment API with DELETE http request")
    public void userCallsDeleteCommentAPIWithDELETEHttpRequest() {
        response = given().pathParam("key", data.getIssueKey())
                .pathParam("id", data.getIdCom()).log().all().filter(session)
                .when().delete(JiraResources.deleteComment())
                .then().log().all().assertThat().statusCode(204)
                .extract().response();
    }

    @When("user calls DeleteIssue API with DELETE http request")
    public void userCallsDeleteIssueAPIWithDELETEHttpRequest() {
        response = given().pathParam("key", data.getIssueKey()).log().all().filter(session)
                .when().delete(JiraResources.deleteIssue())
                .then().log().all().assertThat().statusCode(204)
                .extract().response();
    }
}
