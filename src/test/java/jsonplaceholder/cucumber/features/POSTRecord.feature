Feature: POST Record

  Scenario Outline: Create A New Post Record
    When I post a new post record
    Then POST record response status code is <statusCode>
    And Following POST record response keys corresponds to the ones passed in the request
      | title   |
      | body    |
      | userId  |
    And Following POST record response keys are present in the response
      | id      |
    Examples:
      | statusCode |
      | 201        |