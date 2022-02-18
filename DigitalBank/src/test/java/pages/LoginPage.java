package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import utilities.Driver;
import utilities.EnvironmentManager;

public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(id="username")
    public WebElement usernameInputBox;
    @FindBy(id="password")
    public WebElement passwordInputBox;
    @FindBy(id="submit")
    public WebElement signInButton;

    public void signIn(){
        usernameInputBox.sendKeys(EnvironmentManager.username);
        passwordInputBox.sendKeys(EnvironmentManager.password);
        signInButton.click();
    }


}
