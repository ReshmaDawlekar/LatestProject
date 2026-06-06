#Author: reshmadawlekar41@gma.domain.com


Feature: Verify place api
  
  @Run
  Scenario: Scenario_AddPlace
    Given User calls "ADD_PLACE" Request
   	When User post "ADD_PLACE" payload
    Then "statusCode" must be added with response code for "200"
    Then "scope" must be added with response code for "APP"
    
     @RestAssured
  Scenario: Scenario_AddBook
    Given User calls "ADD_BOOK" Request
   	When User request call for "ADD_BOOK"
    Then "statusCode" must be added with response code for "ADD_BOOK"
  
    @RestAssured
  Scenario: Scenario_GetBooks
    Given User calls "GET_BOOKS" Request
   	When User request call for "GET_BOOKS"
    Then "statusCode" must be added with response code for "GET_BOOKS"
    
    
     @RestAssured
  Scenario Outline: Scenario_GetBook
    Given User calls "GET_BOOK" Request
   	When User request call for "GET_BOOK" with <pathParam> value <pathValue>
    Then "statusCode" must be added with response code for "GET_BOOK"
    Examples:
    |pathParam|pathValue|
    |"id"|"3410"|
    
    
     @RestAssured
  Scenario Outline: Scenario_updateBook
    Given User calls "UPDATE_BOOK" Request
   	When User request call for "UPDATE_BOOK" with <pathParam> value <pathValue>
    Then "statusCode" must be added with response code for "UPDATE_BOOK"
    Examples:
    |pathParam|pathValue|
    |"id"|"786"|
    
     @RestAssured
  Scenario Outline: Scenario_updateBook
    Given User calls "DELETE_BOOK" Request
   	When User request call for "DELETE_BOOK" with <pathParam> value <pathValue>
    Then "statusCode" must be added with response code for "DELETE_BOOK"
    Examples:
    |pathParam|pathValue|
    |"id"|"786"|
    
    