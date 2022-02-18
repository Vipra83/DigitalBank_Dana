package step_defs;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gherkin.lexer.Th;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.LoginPage;
import pages.SavingAccountPage;
import utilities.Driver;
import utilities.EnvironmentManager;

import java.util.List;
import java.util.regex.Pattern;

public class US5Steps {
    WebDriver driver = Driver.getDriver();
    BasePage basePage = new BasePage();
    LoginPage loginPage = new LoginPage();
    SavingAccountPage savingAccountPage = new SavingAccountPage();
    WebDriverWait wait = new WebDriverWait(driver,5);

    @Given("^user created an account and is on the Savings \"([^\"]*)\" page$")
    public void user_created_an_account_and_is_on_the_Savings_page(String arg1) throws InterruptedException {
        driver.get(EnvironmentManager.baseUrl);
        loginPage.signIn();
        Thread.sleep(3000);
        basePage.savingsMenu.click();
        basePage.viewSavings.click();

    }

    @Then("^user should see the \"([^\"]*)\" table$")
    public void user_should_see_the_table(String transactions) {
        Assert.assertEquals("Transactions table is not displayed", transactions,
                savingAccountPage.transactionsTable.getText());
    }

    @Then("^user should see the header \"([^\"]*)\" and date and time when transaction happened$")
    public void user_should_see_the_header_and_date_and_time_when_transaction_happened(String date){
        Assert.assertTrue("Date header is not displayed", savingAccountPage.dateHeader.getText().contains(date));
        Assert.assertTrue("Date displayed has a wrong format", savingAccountPage.isValidDate(savingAccountPage.dateAndTimeField));
    }

    @Then("^user should see the header \"([^\"]*)\" with the type of transaction$")
    public void user_should_see_the_header_with_the_type_of_transaction(String category, DataTable dataTable){
        Assert.assertTrue("Category header is not displayed", savingAccountPage.categoryHeader.getText().contains(category));

        List<String> validTransTypes = dataTable.asList(String.class);
        //Asserting that the list of shown transaction types are included in the allowed transaction types data table provided by reqs
        for(WebElement transaction: savingAccountPage.transactionTypes){
            Assert.assertTrue("Transaction type is invalid", validTransTypes.contains(transaction.getText()));
        }


    }

    @Then("^user should see header \"([^\"]*)\" and transaction id and type of transaction$")
    public void user_should_see_header_and_transaction_id_and_type_of_transaction(String description, DataTable dataTable) {
        Assert.assertTrue("Description column is not displayed", savingAccountPage.descriptionHeader.getText().contains(description));

        for (WebElement transDescription: savingAccountPage.transDescriptionList){
            String pattern = "^\\d{9}\\s\\(\\D{3}\\)\\s\\-\\s.*$";
            Assert.assertTrue("Transaction ID is displayed in invalid format",
                    Pattern.matches(pattern, transDescription.getText()));
        }

    }

    @Then("^user should see the header \"([^\"]*)\" and the amount of transaction$")
    public void user_should_see_the_header_and_the_amount_of_transaction(String amountHeader){
        Assert.assertTrue("Amount header is not displayed", savingAccountPage.amountHeader.getText().contains(amountHeader));

        for (WebElement amount: savingAccountPage.amountList){
            String pattern = "^\\$?-\\d*.\\d{2}$";
            String pattern2 = "^\\$\\d*.\\d{2}$";
            Assert.assertTrue("Amount is displayed in invalid format",
                    Pattern.matches(pattern, amount.getText()) || Pattern.matches(pattern2, amount.getText()));
        }
    }

    @Then("^user should see the header \"([^\"]*)\" and the balance amount after each transaction$")
    public void user_should_see_the_header_and_the_balance_amount_after_each_transaction(String balanceHeader){
        Assert.assertTrue("Balance header is not displayed", savingAccountPage.balanceHeader.getText().contains(balanceHeader));

        for (WebElement amount: savingAccountPage.balanceList){
            String pattern = "^\\$?-\\d*.\\d{2}$";
            String pattern2 = "^\\$\\d*.\\d{2}$";
            Assert.assertTrue("Amount is displayed in invalid format",
                    Pattern.matches(pattern, amount.getText()) || Pattern.matches(pattern2,amount.getText()));
        }
    }

    @Given("^user has more than one saving account$")
    public void user_has_more_than_one_saving_account() {
        Assert.assertTrue("User doesn't have more than one account", savingAccountPage.savingAccounts.size()>1);
    }

    @Then("^only one account should be active there$")
    public void only_one_account_should_be_active_there(){
        int activeAccounts = 0;
        for (WebElement isActiveButton: savingAccountPage.isActiveButtonList){
            if(isActiveButton.isSelected()){
                activeAccounts++;
            }
        }
        Assert.assertTrue("More than one account are selected as active", activeAccounts==1);
    }

    @Then("^user should see transactions history for an active checking account$")
    public void user_should_see_transactions_history_for_an_active_checking_account() {
        String blueBoxBalance = savingAccountPage.balanceBlueBox.getText();
        String transHistoryBalance = savingAccountPage.balanceList.get(0).getText();
        Assert.assertTrue("Transaction history is not for an active account because balances don't match",
                blueBoxBalance.contains(transHistoryBalance));
    }

    @Then("^\"([^\"]*)\" toggle button should be present on each checking accounts$")
    public void toggle_button_should_be_present_on_each_checking_accounts(String toggleButton){
        int savingAcc = savingAccountPage.savingAccounts.size();
        int toggleButtons = savingAccountPage.onOffButton.size();
        Assert.assertTrue("Some accounts are missing " + toggleButton + "buttons", savingAcc==toggleButtons);
    }

}
