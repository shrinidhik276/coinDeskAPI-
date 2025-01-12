Feature: Verify item can be added to Cart on eBay
@cart
  Scenario: Verify item can be added to Cart
    Given I open the "browser"
#    When I navigate to eBay homepage
   And I search for "book"
    And I click on the first book in the list
    And I click on "Add to cart"
    Then the cart should be updated and display the number of items