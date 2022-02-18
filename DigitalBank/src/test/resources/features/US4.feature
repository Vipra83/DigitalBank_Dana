Feature:As a User, I want to have an ability to create a new Savings bank account
  so that my payment can be processed from my preferred account.
  #>
  #Functionalities:
  #1. Display Checking field under BANKING ACCOUNTS section,
  #clicking on 'Savings' dropdown should be displayed with following options: View Savings, New Savings
  #2. Clicking on 'New Savings' option, should redirect user to "http://dbankdemo.com/account/savings-add"
  ####(Note: Click on 'New Savings' and try to open it in new tab)
  #3. Display a header "New Savings Account"
  #4. Display a label "Select Savings Account Type" with the following radio button options:
  #  Savings, Money Market
  #  - All Radio buttons should be unchecked by default
  #5. Display a label "Select Account Ownership" with the following radio button options: Individual, Joint
  #  - All Radio buttons should be unchecked by default
  #6. Display an Input filed "Account Name".
  #- Accepts alphanumeric and special characters.
  #6. Display an Input filed "Initial Deposit Amount"
  #- Minimum opening deposit is $2500.00. Accepts numeric whole or decimal values.
  #7. Display 'Submit' button, clicking on it should successfully create an account
  # and user will be redirected to page to view saving account.
  #8. Display 'Reset' button, clicking on it should reset all filled values to default state.
  #
  #Logic:
  #
  #If any of the following conditions are triggered, it should throw an Error Msg.
  #* Rule 1: Unselected Account Type:
  # Logic: Account Type options are not selected.
  #Error Msg: "Please select one of these options."
  #* Rule 2: Unselected Account Ownership:
  # Logic: Account Ownership options are not selected.
  #Error Msg: "Please select one of these options."
  #* Rule 3: Empty Account Name:
  # Logic: Clicking submit button with an empty Account Name.
  #Error Msg: "Please fill out these field."
  #* Rule 4: Empty Initial Deposit Amount:
  # Logic: Clicking submit button with an empty Initial Deposit Amount.
  #Error Msg: "Please fill out these field."
  #* Rule 5: Invalid Initial Deposit Amount:
  # Logic: Clicking submit button with an invalid Initial Deposit Amount.
  #Error Msg: "Please match the requested format."
  #* Rule 6: Minimum Initial Deposit Amount:
  # Logic: Clicking submit button with Initial Deposit Amount less than a minimum amount.
  #Error Msg: "TThe initial deposit ($enteredAmount) entered does not meet the minimum amount ($2500.00) required. Please enter a valid deposit amount."

#Elena & Assel
  Background:
    Given user is on the "http://3.131.35.165:8080/bank/account/savings-add" page


  Scenario: New Savings Account header validation
    Then user should see a header "New Savings Account"

  Scenario: Select Checking Account Type label and radio buttons validation
    Then user should see a label "Select Savings Account Type" and radio buttons which should be unchecked
      | Savings      |
      | Money Market |

  Scenario: Select Account Ownership label and radio buttons validation
    Then user should see a label "Select Account Ownership" and radio buttons which should be unchecked
      | Individual |
      | Joint      |

  Scenario: Account Name input field validation
    Then user should see an input field "Account Name"

  Scenario: Initial Deposit Amount input field validation
    Then user should see an input field "Initial Deposit Amount"





#Everything is checked and input is correct
  Scenario Outline: Submit button - valid submission
    When user sees a label <label> and checks radio button <type>
    And user sees a label <labelOwner> and checks radio button <ownership>
    And user sees an input field <fieldName> and types "<name>"
    And user sees an input field <fieldAmount> and types <amount>
    And user clicks "Submit" button
    Then new account should be successfully created
    And user will be redirected to page to view savings account
    #http://3.131.35.165:8080/bank/account/savings-view?selectSwitch=273
    Examples:
      | label                       | labelOwner               | type         | ownership  | fieldName    | fieldAmount     | name     | amount |
      | Select Savings Account Type | Select Account Ownership | Savings      | Individual | Account Name | Initial Deposit | John123! | 25.00  |
      | Select Savings Account Type | Select Account Ownership | Money Market | Individual | Account Name | Initial Deposit | John123! | 25.00  |
      | Select Savings Account Type | Select Account Ownership | Savings      | Joint      | Account Name | Initial Deposit | John123! | 25.00  |
      | Select Savings Account Type | Select Account Ownership | Money Market | Joint      | Account Name | Initial Deposit | John123! | 25.00  |


    #selected only 1 radio button - Type
  Scenario Outline: Submit button - selected only Select Savings Account Type
    When user sees a label <label> and checks radio button <type>
    Then user should see "Please select one of these options." message
    Examples:
      | label                       | type         |
      | Select Savings Account Type | Savings      |
      | Select Savings Account Type | Money Market |


    #selected only 1 radio button - Ownership
  Scenario Outline:Submit button - selected only Select Account Ownership
    When user sees a label <labelOwner> and checks radio button <ownership>
    Then user should see "Please select one of these options." message
    Examples:
      | labelOwner               | ownership  |
      | Select Account Ownership | Individual |
      | Select Account Ownership | Joint      |


     #selected only 1 (type input) - Account name
  Scenario Outline: Submit button - input only for Account Name
    When user sees an input field <fieldName> and types "<name>"
    Then user should see "Please select one of these options." message
    Examples:
      | fieldName    | name     |
      | Account Name | John123! |


     #selected only 1 (type input) - Deposit Amount
  Scenario Outline: Submit button - input only for Deposit Amount
    When  user sees an input field <fieldAmount> and types <amount>
    Then user should see "Please select one of these options." message
    Examples:
      | fieldAmount     | amount |
      | Initial Deposit | 25.00  |






#forgot to check Account type
  Scenario Outline: Submit button - unselected Account type
    When user sees a label <labelOwner> and checks radio button <ownership>
    And user sees an input field <fieldName> and types "<name>"
    And user sees an input field <fieldAmount> and types <amount>
    And user clicks "Submit" button
    Then user should see "Please select one of these options." message
    Examples:
      | labelOwner               | ownership  | fieldName              | name     | fieldAmount     | amount |
      | Select Account Ownership | Individual | Account Name           | John123! | Initial Deposit | 25.00  |
      | Select Account Ownership | Joint      | Initial Deposit Amount | John123! | Initial Deposit | 25.00  |


#forgot to check Ownership
  Scenario Outline: Submit button - unselected Account ownership
    When user sees a label <label> and checks radio button <type>
    And user sees an input field <fieldName> and types "<name>"
    And user sees an input field <fieldAmount> and types <amount>
    And user clicks "Submit" button
    Then user should see "Please select one of these options." message

    Examples:
      | label                       | type         | fieldName    | fieldAmount     | name     | amount |
      | Select Savings Account Type | Savings      | Account Name | Initial Deposit | John123! | 25.00  |
      | Select Savings Account Type | Money Market | Account Name | Initial Deposit | John123! | 25.00  |
      | Select Savings Account Type | Savings      | Account Name | Initial Deposit | John123! | 25.00  |
      | Select Savings Account Type | Money Market | Account Name | Initial Deposit | John123! | 25.00  |


#forgot to input Account Name
  Scenario Outline: Submit button - missing Account Name
    When user sees a label <label> and checks radio button <type>
    And  user sees a label <labelOwner> and checks radio button <ownership>
    And user sees an input field <fieldAmount> and types <amount>
    And user clicks "Submit" button
    Then user should see "Please fill out this field." message

    Examples:
      | label                       | labelOwner               | type         | ownership  | fieldAmount     | amount |
      | Select Savings Account Type | Select Account Ownership | Savings      | Individual | Initial Deposit | 25.00  |
      | Select Savings Account Type | Select Account Ownership | Money Market | Individual | Initial Deposit | 25.00  |
      | Select Savings Account Type | Select Account Ownership | Savings      | Joint      | Initial Deposit | 25.00  |
      | Select Savings Account Type | Select Account Ownership | Money Market | Joint      | Initial Deposit | 25.00  |


#forgot to input Deposit Amount
  Scenario Outline: Submit button - missing Initial Deposit Amount
    When user sees a label <label> and checks radio button <type>
    And user sees a label <labelOwner> and checks radio button <ownership>
    And user sees an input field <fieldName> and types "<name>"
    And user clicks "Submit" button
    Then user should see "Please fill out this field." message
    Examples:
      | label                       | labelOwner               | type         | ownership  | fieldName      | name     |
      | Select Savings Account Type | Select Account Ownership | Savings      | Individual | Account Name  | John123! |
      | Select Savings Account Type | Select Account Ownership | Money Market | Individual | Account Name | John123! |
      | Select Savings Account Type | Select Account Ownership | Savings      | Joint      | Account Name | John123! |
      | Select Savings Account Type | Select Account Ownership | Money Market | Joint      | Account Name  | John123! |


# "", ttt, -20  -invalid format, anything except numbers
  Scenario Outline: Submit button - invalid Initial Deposit Amount
    When user sees a label <label> and checks radio button <type>
    And user sees a label <labelOwner> and checks radio button <ownership>
    And user sees an input field <fieldName> and types "<name>"
    And user sees an input field <fieldAmount> and types "<amount>"
    And user clicks "Submit" button
    Then user should see "Please match the requested format." message
    Examples:
      | label                       | labelOwner               | type         | ownership  | fieldName    | fieldAmount     | name     | amount |
      | Select Savings Account Type | Select Account Ownership | Savings      | Individual | Account Name | Initial Deposit | John123! | ""  |
      | Select Savings Account Type | Select Account Ownership | Money Market | Individual | Account Name | Initial Deposit | John123! | -20 |
      | Select Savings Account Type | Select Account Ownership | Savings      | Joint      | Account Name | Initial Deposit | John123! | ttt  |
      | Select Savings Account Type | Select Account Ownership | Money Market | Joint      | Account Name | Initial Deposit | John123! | -  |



# everything is correct and checked
# 0-24.99$ - error, anything less than 25$(min)
  Scenario Outline: Submit button - Initial Deposit Amount less than a minimum amount
    When user sees a label <label> and checks radio button <type>
    And user sees a label <labelOwner> and checks radio button <ownership>
    And user sees an input field <fieldName> and types "<name>"
    And user sees an input field <fieldAmount> and types "<amount>"
    And user clicks "Submit" button
    Then user should see "The initial deposit ($enteredAmount) entered does not meet the minimum amount ($25.00) required. Please enter a valid deposit amount." message
    Examples:
      | label                       | labelOwner               | type         | ownership  | fieldName    | fieldAmount     | name     | amount |
      | Select Savings Account Type | Select Account Ownership | Savings      | Individual | Account Name | Initial Deposit | John123! |  24.99 |
      | Select Savings Account Type | Select Account Ownership | Money Market | Individual | Account Name | Initial Deposit | John123! | 0 |
      | Select Savings Account Type | Select Account Ownership | Savings      | Joint      | Account Name | Initial Deposit | John123! | 5  |
      | Select Savings Account Type | Select Account Ownership | Money Market | Joint      | Account Name | Initial Deposit | John123! | 1.99  |






#BUG!
  # everything is correct and checked
  # only number || only char || only special character - not correct format
  Scenario Outline: Account Name input field validation - incorrect input

    When user sees a label <label> and checks radio button <type>
    And user sees a label <labelOwner> and checks radio button <ownership>
    And user sees an input field <fieldName> and types "<name>"
    And user sees an input field <fieldAmount> and types <amount>
    And user clicks "Submit" button
    Then input field should not accept the input

    Examples:
      | label                       | labelOwner               | type         | ownership  | fieldName    | fieldAmount     | name     | amount |
      | Select Savings Account Type | Select Account Ownership | Savings      | Individual | Account Name | Initial Deposit | john123 | 25.00  |
      | Select Savings Account Type | Select Account Ownership | Money Market | Individual | Account Name | Initial Deposit | #$3  | 25.00  |
      | Select Savings Account Type | Select Account Ownership | Savings      | Joint      | Account Name | Initial Deposit | !$"  | 25.00  |
      | Select Savings Account Type | Select Account Ownership | Money Market | Joint      | Account Name | Initial Deposit | john$! | 25.00  |


  Scenario: Reset button presence validation
    Then user should see "Reset" button

  Scenario Outline: Reset button verification
    When user sees a label <label> and checks radio button <type>
    And user sees a label <labelOwner> and checks radio button <ownership>
    And user sees an input field <fieldName> and types "<name>"
    And user sees an input field <fieldAmount> and types <amount>
    When user clicks "Reset" button
    Then all the input fields should be empty
    Examples:
      | label                       | labelOwner               | type         | ownership  | fieldName    | fieldAmount     | name     | amount |
      | Select Savings Account Type | Select Account Ownership | Savings      | Individual | Account Name | Initial Deposit | John123! | 25.00  |
      | Select Savings Account Type | Select Account Ownership | Money Market | Individual | Account Name | Initial Deposit | John123! | 25.00  |
      | Select Savings Account Type | Select Account Ownership | Savings      | Joint      | Account Name | Initial Deposit | John123! | 25.00  |
      | Select Savings Account Type | Select Account Ownership | Money Market | Joint      | Account Name | Initial Deposit | John123! | 25.00  |
