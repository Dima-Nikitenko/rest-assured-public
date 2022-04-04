Feature: GET Posts

  Scenario Outline: Get List Of All Posts
    When I retrieve all posts
    Then GET posts response status code is <statusCode>
    And GET posts response Content-Type is '<contentType>'
    And Response list is sorted ASC by '<key>'
    Examples:
      | statusCode | contentType | key  |
      | 200        | JSON        | id   |
