@AddPlaceAPI
Feature: Google Add Place API Testing

  Scenario Outline: Validate user is able to add place using Google API
    Given user prepares the payload with "<TestDataSheet>", "<TestDataKey>"
    When user makes a POST request to endpoint
    Then user receives the status code as 200
    And user validates the response body

    Examples:
      | TestDataSheet | TestDataKey       |
      | AddAPI        | AddPlaceValidSet1 |
      | AddAPI        | AddPlaceValidSet2 |