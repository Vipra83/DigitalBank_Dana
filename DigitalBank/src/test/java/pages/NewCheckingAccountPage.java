package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

public class NewCheckingAccountPage {
    public NewCheckingAccountPage () {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy (xpath = "//div[@class='card-header bg-flat-color-1']/*")
    public WebElement newCheckingAccountHeader;

    @FindBy(xpath = "//label[@class = 'form-control-label']/*[contains(text(),'Account Type')]")
    public  WebElement selectCheckingAccType;

    @FindBy (xpath = "//label[@class='form-control-label']//strong[contains(text(),'Ownership')]")
    public WebElement selectOwnershipType;

    @FindBy (id = "name")
    public WebElement inputName;

    @FindBy(id = "openingBalance")
    public WebElement initialDeposit;

    @FindBy (xpath = "//button[@type='submit' and @id='newCheckingSubmit']")
    public WebElement submitButton;

    @FindBy(xpath = "//button[@type='reset' and @id='newCheckingReset']")
    public WebElement resetButton;

    @FindBy(xpath = "//input[@id='Standard Checking']")
    public WebElement standardCheckingRadioButton;

    @FindBy(xpath = "//input[@id='Interest Checking']")
    public WebElement interestCheckingRadioButton;

    @FindBy (xpath = "//input[@id='Individual']")
    public WebElement individualAccountRadioButton;

    @FindBy (xpath = "//input[@id='Joint']")
    public WebElement jointAccountRadioButton;



}
