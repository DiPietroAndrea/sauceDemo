Feature: Login

  Test login with different combination

  Background:

    Given I am on the login page

  Scenario Outline: Login with correct credentials

    When I enter username as '<username>' and password as '<password>'

    Then I land on the Products page

      Examples:
        | username                  | password      |
        | standard_user             | secret_sauce  |
        | problem_user              | secret_sauce  |

        | error_user                | secret_sauce  |
        | visual_user               | secret_sauce  |

  Scenario Outline: Login with incorrect credentials

    When I enter username as '<username>' and password as '<password>'

    Then I have an error message as '<credentialMessage>'

      Examples:
        | username        | password      | credentialMessage                                                         |
        | locked_out_user | secret_sauce  | Epic sadface: Sorry, this user has been locked out.                       |
        |                 | secret_sauce  | Epic sadface: Username is required                                        |
        | standard_user   |               | Epic sadface: Password is required                                        |
        | a               | b             | Epic sadface: Username and password do not match any user in this service |

