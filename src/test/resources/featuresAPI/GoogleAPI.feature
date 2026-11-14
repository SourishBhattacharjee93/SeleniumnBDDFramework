Feature: Google API Testing

  @AddPlaceScenario
  Scenario Outline: Validate user is able to add place using Google API
    Given user prepares the payload with "<lat>", "<lng>", "<name>", "<address>", "<language>"
    When user makes a POST request to endpoint
    Then user receives the status code as 200
    And user validates the response body

    Examples:
      | lat        | lng       | name               | address             | language   |
      | -38.383494 | 33.427362 | The Green Terraces | Neotown, ECity Ph 1 | English-UK |