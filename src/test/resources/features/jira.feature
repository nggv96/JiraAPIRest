Feature: Issues manage

  @CreateIssue
  Scenario: Create new Issue Succesfully
    Given authentication witn user "ngutierrezg96" and password "Medellin.2021"
    When user calls CreateIssue API with POST http request with summary "Issue created automatic"
    Then the API call is success with status code 201