package test.java.co.com.jira.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;
import org.junit.Assert;
import test.resources.apiResources.JiraResources;
import test.resources.payloads.JiraPayloads;

import static io.restassured.RestAssured.*;

public class JiraStepDefinition {

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
    }
    @Then("the API call is success with status code {int}")
    public void theAPICallIsSuccessWithStatusCode(int status) {
        Assert.assertEquals(status, response.getStatusCode());
    }

    @When("user calls CreateComment API with POST http request with comment {string}")
    public void userCallsCreateCommentAPIWithPOSTHttpRequestWithComment(String comment) {
        response = given().pathParam("key","RSA-29").log().all()
                .header("Content-Type", "application/json")
                .body(JiraPayloads.addComment(comment)).filter(session)
                .when().post(JiraResources.addComment())
                .then().log().all().assertThat().statusCode(201)
                .extract().response();
    }

    @When("user calls DeleteComment API with DELETE http request")
    public void userCallsDeleteCommentAPIWithDELETEHttpRequest() {
        response = given().pathParam("key", "RSA-29")
                .pathParam("id", "10203").log().all().filter(session)
                .when().delete(JiraResources.deleteComment())
                .then().log().all().assertThat().statusCode(204)
                .extract().response();
    }

    @When("user calls DeleteIssue API with DELETE http request")
    public void userCallsDeleteIssueAPIWithDELETEHttpRequest() {
        response = given().pathParam("key", "RSA-29").log().all().filter(session)
                .when().delete(JiraResources.deleteIssue())
                .then().log().all().assertThat().statusCode(204)
                .extract().response();
    }
}
