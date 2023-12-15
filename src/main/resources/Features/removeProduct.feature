Feature: Remove a product

  Remove a product from the home page

  Background:

    Given I am on the login page

    When I enter username as 'standard_user' and password as 'secret_sauce'

    And I land on the Products page

    Then I click on the button to add a product


  Scenario: Remove a product

    When I click on the button to remove a product

    Then The button says 'Add to cart'


  Scenario: Remove a product from the detail page

    When I click on the image of a product

    Then I click on the button to remove a product


  Scenario: Remove a product from the cart

    When I click on the cart button

    Then I click on the button to remove a product




