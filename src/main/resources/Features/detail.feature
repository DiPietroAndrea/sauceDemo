Feature: Detail

    Test Detail of a product

  Background:

    Given I am on the login page

    When I enter username as 'standard_user' and password as 'secret_sauce'

    Then I land on the Products page


  Scenario: I go to the detail page of a product

    When I click on the image of a product

    #Then I see the page of detail of 'Sauce Labs Backpack'


  Scenario: I go to the detail page of a product from the title

    When I click on the title of a product

    Then I see the page of detail of 'Sauce Labs Backpack'


  Scenario: I go to the detail page of a product from the cart

    When I click on the button to add a product

    And I click on the cart button

    And I click on the title of a product

    Then I see the page of detail of 'Sauce Labs Backpack'