package step_defs;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.CheckingAccountPage;
import pages.LoginPage;
import utilities.Driver;
import utilities.EnvironmentManager;
import java.util.List;
import java.util.regex.*;



public class US3Steps {
    private static final Logger LOGGER = LogManager.getLogger(US3Steps.class);
    WebDriver driver = Driver.getDriver();
    BasePage basePage = new BasePage();
    LoginPage loginPage = new LoginPage();
    CheckingAccountPage checkingAccountPage = new CheckingAccountPage();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    private String buttonToAppear = " Waiting for visibility of ";
    private String clickOn = "Clicking on ";

    @Given("^user created an account and is on the \"([^\"]*)\" page$")
    public void user_created_and_account_and_is_on_the_page(String typeOfAccount)  {
        LOGGER.info("Navigating to the page");
        driver.get(EnvironmentManager.baseUrl);
        LOGGER.info("Signing in");
        loginPage.signIn();
        LOGGER.info("Refreshing the page");
        driver.navigate().refresh();
        LOGGER.info(buttonToAppear +" CheckingMenu Button");
        wait.until(ExpectedConditions.visibilityOf(basePage.checkingMenu));
        LOGGER.info(clickOn + "CheckingMenu Button");
        basePage.checkingMenu.click();
        LOGGER.info(buttonToAppear +" ViewChecking Button");
        wait.until(ExpectedConditions.visibilityOf(basePage.viewChecking));
        LOGGER.info(clickOn + "ViewChecking Button");
        basePage.viewChecking.click();
    }

    @Then("^user should see \"([^\"]*)\" table$")
    public void user_should_see_table(String transactions)  {
        LOGGER.info(buttonToAppear + "transaction table");
        wait.until(ExpectedConditions.visibilityOf(checkingAccountPage.transactionsTable));
        LOGGER.info("Assertion");
        Assert.assertEquals("Transactions table is not displayed",
                transactions, checkingAccountPage.transactionsTable.getText() );
    }

    @Then("^user should see header \"([^\"]*)\" and date and time when transaction happened$")
    public void user_should_see_header_and_date_and_time_when_transaction_happened(String date) {
        LOGGER.info("Assertion");
        Assert.assertTrue("Date header is not displayed", checkingAccountPage.dateHeader.getText().contains(date));
        LOGGER.info("Assertion");
        Assert.assertTrue("Date displayed has a wrong format", checkingAccountPage.isValidDate(checkingAccountPage.dateAndTimeField));
    }


    @Then("^user should see header \"([^\"]*)\" with the type of transaction$")
    public void user_should_see_header_with_the_type_of_transaction(String category, DataTable dataTable) {
        LOGGER.info("Assertion");
       Assert.assertTrue("Category header is not displayed", checkingAccountPage.categoryHeader.getText().contains(category));

       List<String> validTransTypes = dataTable.asList(String.class);
        LOGGER.info("Assertion");
        //Asserting that the list of shown transaction types are included in the allowed transaction types data table provided by reqs
       for(WebElement transaction: checkingAccountPage.transactionTypes){
           Assert.assertTrue("Transaction type is invalid", validTransTypes.contains(transaction.getText()));
       }
    }

    @Then("^user should see header \"([^\"]*)\", transaction id and transaction merchant$")
    public void user_should_see_header_transaction_id_and_transaction_merchant(String description) {
        LOGGER.info("Assertion");
        Assert.assertTrue("Description column is not displayed", checkingAccountPage.descriptionHeader.getText().contains(description));

        for (WebElement transDescription: checkingAccountPage.transDescriptionList){
            String pattern = "^\\d{9}\\s\\(\\D{3}\\)\\s\\-\\s.*$";
            LOGGER.info("Assertion");
            Assert.assertTrue("Transaction ID is displayed in invalid format",
                    Pattern.matches(pattern, transDescription.getText()));
        }
    }

    @Then("^user should see header \"([^\"]*)\" and the amount of transaction$")
    public void user_should_see_header_and_the_amount_of_transaction(String amountHeader) {
        LOGGER.info("Assertion");
        Assert.assertTrue("Amount header is not displayed", checkingAccountPage.amountHeader.getText().contains(amountHeader));

        for (WebElement amount: checkingAccountPage.amountList){
            String pattern = "^\\$?-\\d*.\\d{2}$";
            String pattern2 = "^\\$\\d*.\\d{2}$";
            LOGGER.info("Assertion");
            Assert.assertTrue("Amount is displayed in invalid format",
                    Pattern.matches(pattern, amount.getText()) || Pattern.matches(pattern2, amount.getText()));
        }

    }

    @Then("^user should see header \"([^\"]*)\" and the balance amount after each transaction$")
    public void user_should_see_header_and_the_balance_amount_after_each_transaction(String balanceHeader) {
        LOGGER.info("Assertion");
        Assert.assertTrue("Balance header is not displayed", checkingAccountPage.balanceHeader.getText().contains(balanceHeader));

        for (WebElement amount: checkingAccountPage.balanceList){
            String pattern = "^\\$?-\\d*.\\d{2}$";
            String pattern2 = "^\\$\\d*.\\d{2}$";
            LOGGER.info("Assertion");
            Assert.assertTrue("Amount is displayed in invalid format",
                    Pattern.matches(pattern, amount.getText()) || Pattern.matches(pattern2,amount.getText()));
        }
    }

    @Given("^user has more than one checking accounts$")
    public void user_has_more_than_one_checking_accounts() {
        LOGGER.info("Assertion");
        Assert.assertTrue("User doesn't have more than one account", checkingAccountPage.checkingAccounts.size()>1);
    }

    @Then("^only one account should be active$")
    public void only_one_account_should_be_active() {
        int activeAccounts = 0;
        for (WebElement isActiveButton: checkingAccountPage.isActiveButtonList){
            if(isActiveButton.isSelected()){
                LOGGER.info("Counting active accounts");
                activeAccounts++;
            }
        }
        LOGGER.info("Assertion");
        Assert.assertTrue("More than one account are selected as active", activeAccounts==1);
    }

    @Then("^user should see transaction history for an active checking account$")
    public void user_should_see_transaction_history_for_an_active_checking_account() {
        String blueBoxBalance = checkingAccountPage.balanceBlueBox.getText();
        String transHistoryBalance = checkingAccountPage.balanceList.get(0).getText();
        LOGGER.info("Assertion");
        Assert.assertTrue("Transaction history is not for an active account because balances don't match",
               blueBoxBalance.contains(transHistoryBalance));
    }

    @Then("^\"([^\"]*)\" toggle button should be present on each checking account$")
    public void toggle_button_should_be_present_on_each_checking_account(String toggleButton) {
        int checkingAcc = checkingAccountPage.checkingAccounts.size();
        int toggleButtons = checkingAccountPage.onOffButton.size();
        LOGGER.info("Assertion");
        Assert.assertTrue("Some accounts are missing " + toggleButton + "buttons", checkingAcc==toggleButtons);
    }


}
