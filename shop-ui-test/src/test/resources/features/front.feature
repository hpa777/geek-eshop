Feature: Test Front Feature

  Scenario: add a product to the cart
    Given I open web browser
    When I navigate to product page
    And I add the product to the cart
    Then check product in the cart