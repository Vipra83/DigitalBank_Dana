Feature: As a User, I want have the ability to create a new Checking bank account
  so that my payment can be processed from my preferred account.
 ###PreferredCheckingAccount###

  #Functionalities:
  #1. Display Checking field under BANKING ACCOUNTS section,
  #clicking on 'Checking' dropdown should be displayed with following options: View Checking, New Checking
  #2. Clicking on 'New Checking' option, should redirect user to "http://3.131.35.165:8080/bank/account/checking-add"
  ###(Note: Click on 'New Checking' and try to open it in new tab) ------
  #3. Display a header "New Checking Account"
  #4. Display a label "Select Checking Account Type" with the following radio button options:
  #  Standard Checking, Interest Checking
  #  - All Radio buttons should be unchecked by default
  #5. Display a label "Select Account Ownership" with the following radio button options: Individual, Joint
  #  - All Radio buttons should be unchecked by default
  #6. Display an Input filed "Account Name".
  #- Accepts alphanumeric and special characters.
  #6. Display an Input filed "Initial Deposit Amount"
  #- Minimum opening deposit is $25.00. Accepts numeric whole or decimal values.
  #7. Display 'Submit' button, clicking on it should successfully create an account
  # and user will be redirected to page to view checking account.
  #8. Display 'Reset' button, clicking on it should reset all filled values to default state.

#Timur

  Background:
    Given user logs in into account using valid credentials
    When user clicks on Checking dropdown
    And user clicks on New Checking option


  Scenario: Display Checking field under BANKING ACCOUNTS section
    Then user should see options
      | View Checking |
      | New Checking  |

  Scenario: Display all the fields necessary for the checking account creation
    Then user should see a header "New Checking Account"
    And user should see a label Select Checking Account Type and radio buttons which should be unchecked
    And user should see a label Select Account Ownership and radio buttons which should be unchecked
    And user should see an input field Account Name
    And user should see an input field Initial Deposit Amount
    And user should see "Submit" button
    And user should see "Reset" button

  Scenario Outline: Positive path - valid name and initial amount
    And user inputs <account type>
    And user inputs <account ownership>
    And user inputs <name>
    And user inputs <deposit> amount
    And user click on "Submit" button
    Then user sees the confirmation message
    Examples:
      | account type      | account ownership | name       | deposit |
      | Standard Checking | Individual        | "Nikki"    | "25.00" |
      | Interest Checking | Individual        | "Nikki"    | "25.00" |
      | Standard Checking | Joint             | "Nikki"    | "25.00" |
      | Interest Checking | Joint             | "Nikki"    | "25.00" |
      | Standard Checking | Individual        | "1234"     | "100"   |
      | Interest Checking | Individual        | "1234"     | "100"   |
      | Standard Checking | Joint             | "1234"     | "100"   |
      | Interest Checking | Joint             | "1234"     | "100"   |
      | Standard Checking | Individual        | "Nikki@23" | "33.34" |
      | Interest Checking | Individual        | "Nikki@23" | "33.34" |
      | Standard Checking | Joint             | "Nikki@23" | "33.34" |
      | Interest Checking | Joint             | "Nikki@23" | "33.34" |


  Scenario Outline: Negative path - valid name and initial amount
    When user inputs <account type>
    And user inputs <account ownership>
    And user inputs <name>
    And user inputs <deposit> amount
    And user click on "Submit" button
    Then user sees the error message for the minimum amount
    Examples:
      | account type      | account ownership | name    | deposit   |
      | Standard Checking | Individual        | "Nikki" | "-239.00" |
      | Interest Checking | Individual        | "Nikki" | "0"       |
      | Standard Checking | Joint             | "Nikki" | "10"      |
      | Interest Checking | Joint             | "Nikki" | "0.00"    |

  Scenario Outline: Negative path - valid name and initial amount
    When user inputs <account type>
    And user inputs <account ownership>
    And user inputs <name>
    And user inputs <deposit> amount
    And user click on "Submit" button
    Then user sees the error message for the name input
    Examples:
      | account type      | account ownership | name | deposit |
      | Standard Checking | Individual        | " "  | "25"    |
      | Interest Checking | Individual        | " "  | "25"    |











