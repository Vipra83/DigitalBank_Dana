package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class SavingAccountPage {
    public SavingAccountPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy (xpath = "//strong[@class='card-title text-light']")
    public WebElement transactionsTable;
    @FindBy (xpath = "//th[@data-field='date']")
    public WebElement dateHeader;
    @FindBy (xpath = "//tr[@role='row']//td[1]")
    public WebElement dateAndTimeField;
    @FindBy (xpath = "//th[@data-field='category']")
    public WebElement categoryHeader;
    @FindBy (xpath = "//tr[@role='row']//td[2]")
    public List<WebElement> transactionTypes;
    @FindBy (xpath = "//th[@data-field='description']")
    public WebElement descriptionHeader;
    @FindBy (xpath = "//tr[@role='row']//td[3]")
    public List<WebElement> transDescriptionList;
    @FindBy(xpath = "//tr[@role='row']//td[4]")
    public List<WebElement> amountList;
    @FindBy (xpath = "//th[@data-field='amount']")
    public WebElement amountHeader;
    @FindBy(xpath = "//th[@data-field='balance']")
    public WebElement balanceHeader;
    @FindBy(xpath = "//tr[@role='row']//td[4]")
    public List<WebElement> balanceList;
    @FindBy(xpath = "//div[@class='col-md-6 col-lg-3']")
    public List<WebElement> savingAccounts;
    @FindBy(xpath = "//input[@type='checkbox']")
    public List <WebElement> isActiveButtonList;
    @FindBy (xpath = "//input[@checked='checked']/parent::label/following-sibling::div[7]")
    public WebElement balanceBlueBox;
    @FindBy (xpath = "//span[@class='switch-label']")
    public List<WebElement> onOffButton;

    public boolean isValidDate(WebElement dateAndTimeField) {
        String inDate = dateAndTimeField.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

}
