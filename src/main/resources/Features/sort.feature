Feature: Sort
  Test sort the products of the home page

  Background:
    Given I am on the login page
    When I enter username as 'standard_user' and password as 'secret_sauce'
    Then I land on the Products page

  Scenario: Sorting products in alphabetical order from A to Z

    When I sort the products from A to Z

    Then The products are sorted by ascending name


  Scenario: Sorting products in alphabetical order from Z to A

    When I sort the products from Z to A

    Then The products are sorted by descending name


  Scenario: Sorting products with lowest price

    When I sort the products from low to high price

    Then The products are sorted by lowest price


  Scenario: Sorting products with the highest price

    When I sort the products from high to low price

    Then The products are sorted by the highest price