Feature: GET Post By Id

  Scenario Outline: Get Specific Post Using Its Id
    When I retrieve post with id = <postId>
    Then GET post by id response status code is <statusCode>
    And GET post by id response keys below have the following values
      | userId | 10    |
      | id     | 99    |
    And Following GET post by id response keys are not empty strings
      | title |
      | body  |
    Examples:
      | postId | statusCode |
      | 99     | 200        |

  Scenario Outline: Get Non-Existent Post Using Its Id
    When I retrieve post with non-existent id = <postId>
    Then Status code is <statusCode>
    And Response body is empty
    Examples:
      | postId | statusCode |
      | 150    | 404        |