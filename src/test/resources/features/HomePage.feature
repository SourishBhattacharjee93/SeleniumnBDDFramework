Feature: Demoblaze Product Store - Homepage

  @Scenario1
  Scenario: Validate the user is able to see the Homepage on launching the Demoblaze product store site
    Given user navigates to the "https://www.demoblaze.com/" url
    Then user validates the Demoblaze product store Homepage is displayed

  @Scenario1
  Scenario: Validate the user is able to click on the Laptops category and see the list of products displayed
    Given user navigates to the "https://www.demoblaze.com/" url
    Then user validates the Demoblaze product store Homepage is displayed
    When user clicks on the Laptops category
    Then user validates the list of products displayed under the Laptops category