Feature: Checkout

    Test of the checkout process

  Background:
    Given I am on the login page

    And I enter username as 'standard_user' and password as 'secret_sauce'

    And I land on the Products page

    When I click on the button to add a product