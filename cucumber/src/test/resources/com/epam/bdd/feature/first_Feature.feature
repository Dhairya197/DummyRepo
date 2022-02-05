Feature: checking Add to cart feature on www.ebay.com
      
  Scenario: Validate if the guest user is able to add a product to cart.
    Given user is on Ebay website landing page 
    When user searches a product "Electric fan" on the homepage.
    Then user should be able to view product information related to product searched.
    And user click on add to cart button
    Then user verifies if the product is added to cart
   
   Scenario Outline: Validate if a registered user is able to place an order
    Given user is on Ebay website landing page 
    When user logs in with "<username>" and "<password>"
    Then user should be able to view homepage.
    When user searches for "<productID>"
    Then user should be able to view product information related to product searched.
    And user click on add to cart button
    Then user verifies if the product is added to cart
   
    
      Examples: 
        | username                | password  | productID |
        | dhairyalala97@gmail.com | Mercer97@ | apple     |