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
}
