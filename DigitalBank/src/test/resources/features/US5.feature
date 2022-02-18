 Feature: Validating all savings accounts page. As a User, I want to have the ability to view all my Savings bank account
   so that I can manage my accounts and see account balance and transactions.

  ### AllSavingsAccounts### - Daniel

  #>As a User,
  #I want to have the ability to view all my Savings bank account
  #so that I can manage my accounts and see account balance and transactions.
  #>
  #
  #Functionalities:
  #1. After User creates a new savings account, user should be able to view savings account information.
  ###All information should match the entered values during the submission of savings account.
  #2. Display "Transactions" table with all transaction details:
  #- Display header "Date": Date and time when transaction happened.
  #- Display header "Category": What kind of transaction was made: Income, Misc etc
  #- Display header "Description": Displays transaction id and type of Transaction
  #- Display header "Amount": Displays the amount of made transaction
  #- Display header "Balance": Displays balance amount, after each transaction
  #3. If User has more than 1 checking accounts, only one of the accounts can be Actived:
  #- Display ON/OFF toggle button on each Savings account
  #- Display transaction history for a particular checking account if it is Activated(ON)
   Background:
     Given user created an account and is on the Savings "Savings Account" page

   Scenario: Transactions table validation
     Then user should see the "Transactions" table
     And user should see the header "Date" and date and time when transaction happened
     And user should see the header "Category" with the type of transaction
       | transaction       |
       | Income            |
       | Bills & Utilities |
       | Food & Dinning    |
       | Misc              |
       | Education         |
       | Gifts & Donations |
       | Business Services |
       | Health & Fitness  |
     And user should see header "Description" and transaction id and type of transaction
       |Deposit             |
       |Withdrawal          |
       |Interest            |
     And user should see the header "Amount" and the amount of transaction
     And user should see the header "Balance" and the balance amount after each transaction

   Scenario: Multiple Accounts in Active account
     And user has more than one saving account
     Then only one account should be active there
     And user should see transactions history for an active checking account
     And "ON/OFF" toggle button should be present on each checking accounts

