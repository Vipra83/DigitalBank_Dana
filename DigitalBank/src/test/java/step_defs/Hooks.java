package step_defs;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import utilities.Driver;
import utilities.EnvironmentManager;

public class Hooks {
    BasePage basePage = new BasePage();
    @Before
    public void setUp() throws Exception {
        //here we can set up our environment variables
        //we will have environment manager that will store all the environment variables
        //and our before hook will call the environment manager to set up all the variables
        //correctly depending on the env we are running our tests against
        EnvironmentManager.setUpEnvironment();

    }

    @After
    public void tearDown(Scenario scenario){
        try{
            if(scenario.isFailed()){
                final byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            }
        } catch (Exception e){
            System.out.println("The error happened while taking screenshot and cleaning up after the test");
            e.getMessage();
        }
        basePage.logoutUser();

        Driver.closeDriver();
    }

}
