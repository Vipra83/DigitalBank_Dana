Feature: Validating all checking accounts page.
  As a User, I want to have the ability to view all my Checking bank account
  so that I can manage my accounts and see account balance and transactions.

  Background:
    Given user created an account and is on the "Checking Account" page

  Scenario: Transactions table validation
    Then user should see "Transactions" table
    And user should see header "Date" and date and time when transaction happened
    And user should see header "Category" with the type of transaction
      | transaction       |
      | Income            |
      | Bills & Utilities |
      | Food & Dinning    |
      | Misc              |
      | Education         |
      | Gifts & Donations |
      | Business Services |
      | Health & Fitness  |
    And user should see header "Description", transaction id and transaction merchant
    And user should see header "Amount" and the amount of transaction
    And user should see header "Balance" and the balance amount after each transaction

  Scenario: Multiple Accounts - Active account
    And user has more than one checking accounts
    Then only one account should be active
    And user should see transaction history for an active checking account
    And "ON/OFF" toggle button should be present on each checking account