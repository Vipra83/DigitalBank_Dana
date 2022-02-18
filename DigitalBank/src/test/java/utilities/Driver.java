package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    //Class that will control our browser and driver
    //it will make sure that there is only one driver instance running at a time
    //in order to prevent anyone including us from creating a driver instance from anywhere
    //we create private contructor

    //this line will not allow you to type Driver = new Driver
    //1 -> for creating a driver
    //2 -> for closing your driver
    private Driver() {
    }
    // singleton design pattern, which is achieved by creating a private constructor
    // to create an object we have to create a public static getDriver method

    private static WebDriver driver;

    public static WebDriver getDriver() {
        //before creating a driver we need to make sure there is no driver running
        //first of all, we have to check that our driver is null
        if (driver == null) {
            //here we will create a new fresh driver base on the browser property
            switch (ConfigReader.getProperty("browser").toLowerCase()) {
                default:
                    //here will be set up for chrome, which is my default browser
                    driver = ChromeWebDriver.loadChromeDriver();
                    break;
                case "firefox":
                    //here will be a code to set up FireFox browser
                    driver = FirefoxWebDriver.loadFirefoxDriver();
                    break;
                case "safari":
                    //here is the setup for safari
                    driver = new SafariDriver();
                    driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
                    driver.manage().window().maximize();
                    break;


            }

        }
            //if it is not null we will return existing driver
            return driver;

    }
 /*   public static void takesScreenshot(Scenario scenario) throws IOException {
        try {
            if (scenario.isFailed()){
                final byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            }
        } catch (Exception e){
            System.out.println("Exception happened while taking screenshot" + e.getMessage());
        }
    }*/

    public static void closeDriver(){
        try {
            if(driver!=null){
                driver.close();
                driver.quit();
                driver = null;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
