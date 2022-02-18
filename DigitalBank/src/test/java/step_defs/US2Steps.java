package step_defs;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.NewCheckingAccountPage;
import utilities.Driver;

import java.util.List;

public class US2Steps {

    BasePage basePage = new BasePage();
    NewCheckingAccountPage newCheckingAccountPage = new NewCheckingAccountPage();


    @When("^user clicks on Checking dropdown$")
    public void user_clicks_on_dropdown() throws InterruptedException {
        Thread.sleep(3000);
        basePage.checkingMenu.click();

    }

    @Then("^user should see options$")
    public void user_should_see_options(DataTable options) {
        List<String> checkingOptions = options.asList(String.class);

        for (String option : checkingOptions) {
            assertTrue(option.contains(basePage.viewChecking.getText())
                    || option.contains(basePage.newChecking.getText()));
        }
    }

    @And("^user clicks on New Checking option$")
    public void user_clicks_on_option() throws InterruptedException {
       /* WebDriverWait wait =  new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(basePage.newChecking));
        wait.until(ExpectedConditions.elementToBeClickable(basePage.newChecking));*/
        Thread.sleep(3000);
        basePage.newChecking.click();
    }

    @Then("^user should see a header \"([^\"]*)\"$")
    public void user_should_see_a_header(String newCheckingAccountHeaderS) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 6);
        wait.until(ExpectedConditions.visibilityOf(newCheckingAccountPage.newCheckingAccountHeader));
        assertTrue("New Checking Account header is not displayed",
                newCheckingAccountPage.newCheckingAccountHeader.isDisplayed());
        assertTrue("New Checking Account header doesn't display correct text",
                newCheckingAccountPage.newCheckingAccountHeader.getText().contains(newCheckingAccountHeaderS));
    }

    @Then("^user should see a label \"([^\"]*)\" and radio buttons which should be unchecked$")
    public void user_should_see_a_label_and_radio_buttons_which_should_be_unchecked(String header, DataTable radioButtons) {

        assertFalse(newCheckingAccountPage.standardCheckingRadioButton.isSelected());
        assertFalse(newCheckingAccountPage.interestCheckingRadioButton.isSelected());

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 6);
        wait.until(ExpectedConditions.visibilityOf(newCheckingAccountPage.selectCheckingAccType));
        assertTrue("Select account type is not displayed",
                newCheckingAccountPage.selectCheckingAccType.isDisplayed());
        assertTrue("Select account type doesn't display correct text",
                newCheckingAccountPage.selectCheckingAccType.getText().equals(header));
    }


    @And("^user should see an input field Account Name$")
    public void userShouldSeeAnInputFieldAccountName() {
        assertTrue("Account name is not displayed", newCheckingAccountPage.inputName.isDisplayed());
    }

    @And("^user should see an input field Initial Deposit Amount$")
    public void userShouldSeeAnInputFieldInitialDepositAmount() {
        assertTrue("Initial deposit field is not displayed", newCheckingAccountPage.initialDeposit.isDisplayed());
    }


    @Then("^user should see \"([^\"]*)\" button$")
    public void user_should_see_button(String submit) {
        assertTrue("Submit button is not displayed", newCheckingAccountPage.submitButton.isDisplayed());
        assertTrue("Submit button doesn't show SUBMIT",
                newCheckingAccountPage.submitButton.getText().contains(submit));
    }
}
