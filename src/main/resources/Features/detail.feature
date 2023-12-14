Feature: Detail
    Test Detail of a product

  Background:
    Given I am on the login page
    When I enter username as 'standard_user' and password as 'secret_sauce'
    Then I land on the Products page

  Scenario: