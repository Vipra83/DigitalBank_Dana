Feature: Viewing account information

  Background:
    Given user logs in into account using valid credentials

  Scenario: Positive scenario, valid login
    Then user should see Welcome John on the right top header

  Scenario: BANKING ACCOUNTS title: Positive scenario, valid login
    Then user should see the following title
      | title       |
      | Checking     |
      | Savings     |
      | TRANSACTIONS / TRANSFERS |

  Scenario Outline: Validating Account titles
    Then user should see <titles> title
    Examples:
      | titles         |
      | Home           |
      | Digital credit |
      | Transfer       |
      | Visa Direct    |

