Feature: Login
  Test login with different combination

  Background:
    Given I am on the login page

  Scenario Outline: Login with correct credentials

    When I enter username as '<username>' and password as '<password>'

    Then I land on the Products page
    Examples:
      | username        | password       |
      | standard_user   | secret_sauce   |
      | locked_out_user | secret_sauce   |
      | standard_us     | secret_sauce   |