package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class ChromeWebDriver {
    public static WebDriver loadChromeDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximazied");
        options.addArguments("--disable-extensions");
        options.addArguments("--window-size=1920,1080");
        //options.addArguments("--no-sandbox");
        //options.addArguments("--disable-gpu");
        if(ConfigReader.getProperty("headless").equalsIgnoreCase("true")){
            options.addArguments("--headless");
        }


        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;

    }
}
