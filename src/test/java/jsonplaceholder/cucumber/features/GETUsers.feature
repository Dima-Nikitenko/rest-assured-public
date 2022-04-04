Feature: GET Users

  Scenario Outline: Get List Of All Users
    When I retrieve all users
    Then GET users response status code is <statusCode>
    And GET users response Content-Type is '<contentType>'
    And User with '<key>' = <value> has data like in '<fileName>' file
    Examples:
      | statusCode | contentType | key  | value | fileName     |
      | 200        | JSON        | id   | 5     | sampleUser   |
