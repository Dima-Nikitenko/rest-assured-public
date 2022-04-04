Feature: GET User By Id

  Scenario Outline: Get Specific User Using Its Id
    When I retrieve user with id = <userId>
    Then GET user by id response status code is <statusCode>
    And Response user has data like in '<fileName>' file
    Examples:
      | userId | statusCode | fileName   |
      | 5      | 200        | sampleUser |