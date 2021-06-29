Feature: Issues manage

  @CreateIssue
  Scenario: Create new Issue Succesfully
    Given authentication witn user "ngutierrezg96" and password "Medellin.2021"
    When user calls CreateIssue API with POST http request with summary "Issue created automatic"
    Then the API call is success with status code 201 and comply with the schema 1

  @CreateCommentInIssue
  Scenario: Create new comment in to Issue Succesfully
    Given authentication witn user "ngutierrezg96" and password "Medellin.2021"
    When user calls CreateComment API with POST http request with comment "New comment in to issue"
    Then the API call is success with status code 201 and comply with the schema 2

  @DeleteCommentInIssue
  Scenario: Delete comment in to Issue Succesfully
    Given authentication witn user "ngutierrezg96" and password "Medellin.2021"
    When user calls DeleteComment API with DELETE http request
    Then the API call is success with status code 204  and comply with the schema 3

  @DeleteIssue
  Scenario: Delete Issue Succesfully
    Given authentication witn user "ngutierrezg96" and password "Medellin.2021"
    When user calls DeleteIssue API with DELETE http request
    Then the API call is success with status code 204  and comply with the schema 4