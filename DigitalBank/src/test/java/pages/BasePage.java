package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

import java.util.Arrays;
import java.util.List;

public class BasePage {
    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    WebDriver driver;
    @FindBy(xpath = "//li[contains(text(),'Welcome')]")
    public WebElement welcomeMessage;

    @FindBy(id = "home-menu-item")
    public WebElement homeLink;
    @FindBy(id = "checking-menu")
    public WebElement checkingMenu;

    @FindBy(xpath = "//a[@id='view-checking-menu-item']")
    public WebElement viewChecking;

    @FindBy(id = "savings-menu")
    public WebElement savingsMenu;

    @FindBy(id = "view-savings-menu-item")
    public  WebElement viewSavings;

    @FindBy(xpath = "//h3[contains(text(),'Transactions')]")
    public WebElement transactionTitle;

    @FindBy(id = "visa-transfer-menu-item")
    public WebElement visaDirectTransfer;
    @FindBy(id = "transfer-menu-item")
    public WebElement transferBetweenAcc;

    @FindBy(xpath = "//h3[contains(text(),'Transfers')]")
    public WebElement transactionsAndTitles;


    @FindBy(xpath = "//img[@class='user-avatar rounded-circle']")
    public WebElement userPictureButton;
    @FindBy(xpath = "//a[text()='Logout']")
    public WebElement logoutButton;

    @FindBy(id = "new-checking-menu-item")
    public WebElement newChecking;





    public void logoutUser(){
        userPictureButton.click();
        logoutButton.click();
    }

    public List<String> getTitles(){
        return Arrays.asList(checkingMenu.getText(), savingsMenu.getText(),transactionTitle.getText());
    }



}
