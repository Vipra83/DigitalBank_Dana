package step_defs;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.junit.Ignore;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.LoginPage;
import utilities.Driver;
import utilities.EnvironmentManager;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class US1Steps {
    LoginPage loginPage = new LoginPage();
    BasePage basePage = new BasePage();
    WebDriver driver = Driver.getDriver();

    @Given("^user logs in into account using valid credentials$")
    public void user_logs_in_into_account_using_valid_credentials()  {
        driver.get(EnvironmentManager.baseUrl);
        loginPage.signIn();
    }

    @Then("^user should see Welcome John on the right top header$")
    public void user_should_see_Welcome_John_on_the_right_top_header()  {
        Assert.assertTrue("\"Welcome John\" is not displayed", basePage.welcomeMessage.isDisplayed());
    }

    @Then("^user should see Home title$")
    public void user_should_see_Home_title()  {
        Assert.assertTrue("\"Home Title\" is not displayed", basePage.homeLink.isDisplayed());

    }

    @Then("^user should see the following title$")
    public void user_should_see_the_following_title(List<Map<String, String>> mapOfTitles)  {

        List <String> titles = mapOfTitles.stream()
        .map(map -> map.get("title"))
                .collect(Collectors.toList());
        //System.out.println(titles);
        //System.out.println(basePage.getTitles());

            Assert.assertTrue(  "One of the titles is not displayed", basePage.getTitles().containsAll(titles) );

    }

    @Ignore
    @Then("^user should see Digital credit title$")
    public void user_should_see_Digital_credit_title()  {

    }

    @Then("^user should see Transfer title$")
    public void user_should_see_Transfer_title()  {
        Assert.assertTrue("Transfer credit title is not displayed", basePage.transferBetweenAcc.isDisplayed());
    }

    @Then("^user should see Visa Direct title$")
    public void user_should_see_Visa_Direct_title()  {
        Assert.assertTrue("Visa Direct Title is not displayed", basePage.visaDirectTransfer.isDisplayed());
    }
}
