package pages.smb;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import pages.PageSupporter;

/**
 * 
 * 
 * @author         :VNatarajan
 * @since          :Oct 9, 2017
 * @filename       :CompanyAndLocationDetailsPage.java
 * @version		   :1.0
 * @description    :
 */
public class CompanyAndLocationDetailsPage extends PageSupporter{

	WebDriver driver;
	public CompanyAndLocationDetailsPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	@FindBy(how=How.ID,using="companyName")
	private WebElement companyName;

	@FindBy(how=How.ID,using="firstName")
	private WebElement firstName;

	@FindBy(how=How.ID,using="lastName")
	private WebElement lastName;

	@FindBy(how=How.ID,using="emailAddress")
	private WebElement email;

	@FindBy(how=How.ID,using="phoneNumber")
	private WebElement phoneNumber;

	@FindBy(how=How.ID,using="ProtectedClassQuestion")
	private WebElement securityQuestion;

	@FindBy(how=How.ID,using="protectedClassAnswer")
	private WebElement securityAnswer;

	@FindBy(how=How.ID,using="streetAddress")
	private WebElement street;

	@FindBy(how=How.ID,using="unitName")
	private WebElement unit;

	@FindBy(how=How.ID,using="city")
	private WebElement city;

	@FindBy(how=How.ID,using="state")
	private WebElement state;

	@FindBy(how=How.ID,using="accountElectric")
	private WebElement electricAccountNo;

	@FindBy(how=How.ID,using="serviceLocationDetailsCheckBox")
	private WebElement billingAddressCheckBox;

	@FindBy(how=How.ID,using="streetAddressBilling")
	private WebElement billingStreet;

	@FindBy(how=How.ID,using="unitNameBilling")
	private WebElement billingUnit;

	@FindBy(how=How.ID,using="cityBilling")
	private WebElement billingCity;

	@FindBy(how=How.ID,using="stateBilling")
	private WebElement billingState;

	@FindBy(how=How.ID,using="BillingAddress_ZipCode")
	private WebElement billingZip;

	@FindBy(how=How.NAME,using="ReviewAndConfirm")
	private WebElement reviewAndConfirm;

	/**
	 * [Filling in the company info in SMB enrollment]
	 * @param companyName1
	 * @param firstName1
	 * @param lastName1
	 * @param email1
	 * @param phoneNumber1
	 */
	public void fillInCompanyInfo(String companyName1,String firstName1,String lastName1,String email1,String phoneNumber1){
		companyName.clear();
		companyName.sendKeys(companyName1);
		firstName.clear();
		firstName.sendKeys(firstName1);
		lastName.clear();
		lastName.sendKeys(lastName1);
		email.clear();
		email.sendKeys(email1);
		phoneNumber.clear();
		phoneNumber.sendKeys(phoneNumber1);
	}
	/**
	 * 
	 * @param securityQuestion1
	 * @param securityAnswer1
	 */
	public void fillInSecurityQuestions(String securityQuestion1,String securityAnswer1){
		Select Security = new Select(securityQuestion);
		Security.selectByValue(securityQuestion1);
		//Security.selectByVisibleText(securityQuestion1);
		securityAnswer.clear();
		securityAnswer.sendKeys(securityAnswer1);			
	}
	/**
	 * 
	 * @param street1
	 * @param unit1
	 * @param city1
	 * @param accountNo
	 */
	public void fillInServiceDetails(String street1,String unit1,String city1,String accountNo){
		street.clear();
		street.sendKeys(street1);
		unit.clear();
		unit.sendKeys(unit1);
		city.clear();
		city.sendKeys(city1);
		electricAccountNo.clear();
		electricAccountNo.sendKeys(accountNo);//24353-56755-55555-555	
		billingAddressCheckBox.click();
	}
	/**
	 * 
	 * @param bStreet
	 * @param bUnit
	 * @param bCity
	 * @param bState
	 * @param bZip
	 */
	public void fillInBillingAddress(String bStreet,String bUnit,String bCity,String bState,String bZip){
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		billingStreet.clear();
		billingStreet.sendKeys("Street Name");
		billingUnit.clear();
		billingUnit.sendKeys("0003");
		billingCity.clear();
		billingCity.sendKeys("Dallas");	
		Select StateDropdown = new Select(billingState);
		StateDropdown.selectByVisibleText("TX");
		billingZip.sendKeys(Keys.RETURN);
		billingZip.sendKeys("9");
		executor.executeScript("arguments[0].setAttribute('value', '" + 07001 +"')", billingZip);
	}
	/**
	 * 
	 */
	public void submitReviewAndConfirm(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",reviewAndConfirm);
	}
}
