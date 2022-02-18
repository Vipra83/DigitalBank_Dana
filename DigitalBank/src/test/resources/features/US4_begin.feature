Feature:As a User, I want have the ability to create a new Savings bank account
  so that my payment can be processed from my preferred account. Beginning

Scenario: Savings field validation
Given user logs into DigitalBank with valid credentials
When user sees "Savings" field under BANKING ACCOUNTS section
And user clicks on "Savings" dropdown
Then user should see options
| View Savings |
| New Savings  |

Scenario: New Savings field validation
Given clicks on "Savings" dropdown and sees "New savings" option
When user clicks on "New savings" option
Then user should be navigated to "http://3.131.35.165:8080/bank/account/savings-add"

Scenario: New Savings field validation through new tab
Given clicks on "Savings" dropdown and sees "New savings" option
When user clicks using right click and opens new tab
Then user should be navigated to "http://3.131.35.165:8080/bank/account/savings-add"