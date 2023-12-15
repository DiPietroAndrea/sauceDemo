Feature: add product

  Test add a product to the cart

  Background:

    Given I am on the login page

    When I enter username as 'standard_user' and password as 'secret_sauce'

    Then I land on the Products page


  Scenario: I add a product to the cart

    When I click on the button to add a product

    Then The cart will be updated to "1"

    But I click on the button to remove a product


  Scenario: I add a product from the detail page

    When I click on the image of a product

    Then I click on the button to add a product

    But I click on the button to remove a product