package pages.myaccount;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import pages.PageSupporter;
/**
 * 
 * @author         :VNatarajan
 * @since          :Sep 20, 2017
 * @filename       :AdminPage.java
 * @version		   :1.0
 * @description    :Page web elements and all functional features of Admin page in My Account 2.0
 */
public class AdminPage extends PageSupporter{
	WebDriver driver;
	public AdminPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	@FindBy(how=How.CSS,using="input[value='ManagementCompanyName']")
	private WebElement managementCompanyNameRadio;

	@FindBy(how=How.ID,using="inputManagementCompanyName")
	private WebElement managementCompanyName;

	@FindBy(how=How.CSS,using="input[value='AccountNumber']")
	private WebElement accountNumberRadio;

	@FindBy(how=How.ID,using="inputAccountNumber")
	private WebElement accountNumber;

	@FindBy(how=How.CSS,using="input[value='CustomerName']")
	private WebElement customerNameRadio;

	@FindBy(how=How.ID,using="inputCustomerName")
	private WebElement customerName;

	@FindBy(how=How.CSS,using="input[value='CustomerNumber']")
	private WebElement customerNumberRadio;

	@FindBy(how=How.ID,using="inputCustomerNumber")
	private WebElement customerNumber;

	@FindBy(how=How.CSS,using="input[value='LDCAccountNumber']")
	private WebElement ldcAccountNumberRadio;

	@FindBy(how=How.ID,using="inputLDCAccountNumber")
	private WebElement ldcAccountNumber;

	@FindBy(how=How.CSS,using="input[value='DemandResponse']")
	private WebElement demandResponseRadio;

	@FindBy(how=How.ID,using="inputDemandCustomerNumber")
	private WebElement drCustomerNo;

	@FindBy(how=How.ID,using="inputDemandVendor")
	private WebElement drVendorId;

	@FindBy(how=How.CSS,using="input[value='Search']")
	private WebElement search;

	@FindBy(how=How.CSS,using="td[ng-hide*='send'] input[ng-model='customer.selected']")
	private WebElement selectCustomer;

	@FindBy(how=How.CSS,using="td[ng-show*='add'] input[ng-model='customer.selected']")
	private WebElement addCustomerCheck;
	
	@FindBy(how=How.CSS,using="div[ng-click*='viewCustomers']")
	private WebElement viewCustomers;

	@FindBy(how=How.LINK_TEXT,using="Home")
	private WebElement home;

	@FindBy(how=How.PARTIAL_LINK_TEXT,using="New Message")
	private WebElement newMessage;

	@FindBy(how=How.CSS,using="a[ng-click*='showSearch']")
	private WebElement changeCustomer;

	@FindBy(how=How.NAME,using="subject")
	private WebElement subject;

	@FindBy(how=How.NAME,using="message")
	private WebElement message;

	@FindBy(how=How.LINK_TEXT,using="Send")
	private WebElement send;

	@FindBy(how=How.CSS,using="button[ng-click*='ok']")
	private WebElement OK;

	@FindBy(how=How.CSS,using="a[ng-click*='loadCustomer'][class=ng-binding]")
	private WebElement internalId;

	@FindBy(how=How.XPATH,using="//th/div[text()='Sent']")
	private WebElement sortBySent;

	@FindAll(value = { @FindBy(how=How.CSS,using="tr[ng-repeat*='message in $data']") })
	private List<WebElement> messageRows;

	@FindAll(value = { @FindBy(how=How.CSS,using="tr[ng-repeat*='user in $data']") })
	private List<WebElement> userRows;
	
	@FindBy(how=How.CSS,using="a[data-target='#admin-selected-customer-refine']")
	private WebElement searchUserLink;
	
	@FindBy(how=How.ID,using="inputSearchUsersEmail")
	private WebElement userEmail;
	
	@FindBy(how=How.ID,using="btnSearchUsersSearch")
	private WebElement searchUserBtn;
	
	@FindBy(how=How.CSS,using="a[ng-click*='editUser']")
	private WebElement editUser;
	
	@FindBy(how=How.CSS,using="select[ng-model='user.UserRole']")
	private WebElement userRole;
	
	@FindBy(how=How.CSS,using="div[ng-click*='saveUser']")
	private WebElement saveUser;

	@FindBy(how=How.NAME,using="LastName")
	private WebElement userLastName;
	
	@FindBy(how=How.NAME,using="FirstName")
	private WebElement userfirstName;
	
	@FindBy(how=How.ID,using="EmailAddress")
	private WebElement newUserEmail;
	
	@FindAll(value = { @FindBy(how=How.NAME,using="CompanyName") })
	private List<WebElement> companyName;
	
	@FindBy(how=How.CSS,using="div[ng-click*='addCustomerUser']")
	private WebElement addCustomerUser;
	
	@FindBy(how=How.CSS,using="div[ng-click*='addCustomer']")
	private WebElement addCustomer;
	
	@FindBy(how=How.CSS,using="input[ng-click*='addCustomer']")
	private WebElement addCustomerBtn;
	
	@FindBy(how=How.CSS,using="div[path-href*='add-customer-user']")
	private WebElement doneBtn;
	
	@FindBy(how=How.CSS,using="div[ng-click*='saveUser']")
	private WebElement sendInvite;
	
	@FindBy(how=How.CSS,using=".alert-success")
	private WebElement successMsg;
	
	@FindBy(how=How.ID,using="inputSearchUsersLastName")
	private WebElement searchUsersLastName;
	
	@FindBy(how=How.CSS,using="input[ng-model='Username']")
	private WebElement username;
	
	@FindBy(how=How.CSS,using="input[ng-model='CustomerName']")
	private WebElement customerName1;
	
	@FindBy(how=How.CSS,using="input[ng-model='customer.Prime']")
	private WebElement primeCheckbox;
	
	@FindBy(how=How.CSS,using="a[ng-click*='savePreferences']")
	private WebElement savePreferences;
	
	public void savePreferences(String userName,String customerName) {
		waitFor(username,30);
		if(!userName.equalsIgnoreCase("not_found")) {
			username.sendKeys(userName);
		}
		if(!customerName.equalsIgnoreCase("not_found")) {
			customerName1.sendKeys(customerName);
		}
		waitFor(primeCheckbox,30);
		primeCheckbox.click();
		waitFor(savePreferences,30);
		savePreferences.click();
		waitFor(successMsg,30);
		primeCheckbox.click();
		savePreferences.click();
		waitFor(successMsg,30);
	}
	
	public void searchCustomersInAdmin(String searchCriteria,String searchValue){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions action = new Actions(driver);
		
		waitFor(managementCompanyNameRadio,120);
		js.executeScript("scroll(0, 200);");
		wait(3);
		switch(searchCriteria.trim()) {

		case "Management Company" :
			managementCompanyNameRadio.click();
			managementCompanyName.sendKeys(searchValue);
			break;

		case "Account Number" :
			accountNumberRadio.click();
			accountNumber.sendKeys(searchValue);
			break;

		case "Customer Name" :
			customerNameRadio.click();
			customerName.sendKeys(searchValue);	
			break;

		case "Customer Number" :
			customerNumberRadio.click();
			customerNumber.sendKeys(searchValue);
			break;

		case "LDC Account Number" :
			ldcAccountNumberRadio.click();
			ldcAccountNumber.sendKeys(searchValue);
			break;

		case "Demand Response Customer Number" :
			demandResponseRadio.click();
			drCustomerNo.sendKeys(searchValue);
			break;

		case "Demand Response Vendor Id" :
			demandResponseRadio.click();
			drVendorId.sendKeys(searchValue);
			break;
		}

		js.executeScript("scroll(0, 250);");
		wait(3);
		search.click();
		waitFor(selectCustomer,45);
		js.executeScript("arguments[0].scrollIntoView(true);", selectCustomer);
		action.moveToElement(selectCustomer).click().perform();
		action.moveToElement(viewCustomers).click().perform();
		waitFor(home,45);
		Assert.assertTrue(home.isDisplayed());
	}

	
	public void sendMessages(String senderEmail,String searchCriteria,String searchValue,String subject1,String message1) {

		waitFor(newMessage,90);
		newMessage.click();
		waitFor(changeCustomer,45);
		changeCustomer.click();
		waitFor(managementCompanyNameRadio,90);

		switch(searchCriteria.trim()) {

		case "Management Company" :
			managementCompanyNameRadio.click();
			managementCompanyName.sendKeys(searchValue);
			break;

		case "Account Number" :
			accountNumberRadio.click();
			accountNumber.sendKeys(searchValue);
			break;

		case "Customer Name" :
			customerNameRadio.click();
			customerName.sendKeys(searchValue);	
			break;

		case "Customer Number" :
			customerNumberRadio.click();
			customerNumber.sendKeys(searchValue);
			break;

		case "LDC Account Number" :
			ldcAccountNumberRadio.click();
			ldcAccountNumber.sendKeys(searchValue);
			break;

		case "Demand Response Customer Number" :
			demandResponseRadio.click();
			drCustomerNo.sendKeys(searchValue);
			break;

		case "Demand Response Vendor Id" :
			demandResponseRadio.click();
			drVendorId.sendKeys(searchValue);
			break;
		}

		search.click();
		waitFor(internalId,45);
		internalId.click();
		waitFor(subject,10);
		subject.sendKeys(subject1);
		message.sendKeys(message1);
		send.click();
		waitFor(OK,15);
		OK.click();
		wait(5);
		waitFor(sortBySent,45);
		sortBySent.click();
		for(WebElement row:messageRows) {
			if(messageRows.indexOf(row)<10) {
				String rowContent = row.getText().toLowerCase();
				if(rowContent.contains(senderEmail.toLowerCase()) && rowContent.contains(subject1.toLowerCase()) && rowContent.contains(message1.toLowerCase())) {
					Assert.assertTrue(true);
					return;
				}
			}
		}
		Assert.assertTrue(false);	
	}
	
	
	public void editUserRole(String email,String newRole,String companyName1) {
		Random rand = new Random();
		int  randomNo = rand.nextInt(10000) + 1;
		waitFor(searchUserLink,120);
		searchUserLink.click();
		waitFor(userRows,30);
		userEmail.sendKeys(email);
		searchUserBtn.click();
		waitFor(editUser,30);
		editUser.click();
		waitFor(userRole,30);
		userLastName.clear();
		userLastName.sendKeys("Automation_"+randomNo);
		Select select = new Select(userRole);
		select.selectByValue(newRole);
		if(companyName.size()>0) {
			companyName.get(0).clear();
			companyName.get(0).sendKeys(companyName1);	
		}
		Actions action = new Actions(driver);
		action.moveToElement(saveUser).click().perform();
		
		waitFor(searchUserLink,60);
		searchUserLink.click();
		waitFor(userRows,30);
		userEmail.sendKeys(email);
		searchUserBtn.click();
		waitFor(editUser,30);
		
		for(WebElement row:userRows) {
			if(userRows.indexOf(row)<10) {
				String rowContent = row.getText().toLowerCase();
				if(rowContent.contains(email.toLowerCase()) && rowContent.contains("Automation_".toLowerCase()+randomNo)) {
					Assert.assertTrue(true);
					return;
				}
			}
		}
		Assert.assertTrue(false);
	}
	
	
	
	public void editUserInfo(String email) {
		Random rand = new Random();
		int  randomNo = rand.nextInt(10000) + 1;
		waitFor(searchUserLink,120);
		searchUserLink.click();
		waitFor(userRows,30);
		userEmail.sendKeys(email);
		searchUserBtn.click();
		waitFor(editUser,30);
		editUser.click();
		waitFor(userRole,30);
		userLastName.clear();
		userLastName.sendKeys("Automation_"+randomNo);
		Actions action = new Actions(driver);
		action.moveToElement(saveUser).click().perform();
		waitFor(searchUserLink,60);
		searchUserLink.click();
		waitFor(userEmail,30);
		userEmail.sendKeys(email);
		searchUsersLastName.sendKeys("Automation_"+randomNo);
		searchUserBtn.click();
		waitFor(editUser,30);
		
		for(WebElement row:userRows) {
			if(userRows.indexOf(row)<10) {
				String rowContent = row.getText().toLowerCase();
				if(rowContent.contains(email.toLowerCase()) && rowContent.contains("Automation_".toLowerCase()+randomNo)) {
					Assert.assertTrue(true);
					return;
				}
			}
		}
		
		Assert.assertTrue(false);
	}
	
	public void addUserWithACustomer(String firstName,String lastName,String userEmail,String searchCriteria,String searchValue ) {
		waitFor(addCustomerUser,45);
		addCustomerUser.click();
		waitFor(userfirstName,15);
		userfirstName.sendKeys(firstName);
		userLastName.sendKeys(lastName);
		newUserEmail.sendKeys(userEmail);
		addCustomer.click();
		/*waitFor(addCustomer,30);
		addCustomer.click();*/
		searchCustomer(searchCriteria,searchValue);
		waitFor(addCustomerCheck,45);
		addCustomerCheck.click();
		addCustomerBtn.click();
		waitFor(successMsg,30);
		doneBtn.click();
		waitFor(sendInvite,30);
		sendInvite.click();
		waitFor(addCustomerUser,30);
		
	}
	
	private void searchCustomer(String searchCriteria,String searchValue) {
		waitFor(managementCompanyNameRadio,45);

		switch(searchCriteria.trim()) {

		case "Management Company" :
			managementCompanyNameRadio.click();
			managementCompanyName.sendKeys(searchValue);
			break;

		case "Account Number" :
			accountNumberRadio.click();
			accountNumber.sendKeys(searchValue);
			break;

		case "Customer Name" :
			customerNameRadio.click();
			customerName.sendKeys(searchValue);	
			break;

		case "Customer Number" :
			customerNumberRadio.click();
			customerNumber.sendKeys(searchValue);
			break;

		case "LDC Account Number" :
			ldcAccountNumberRadio.click();
			ldcAccountNumber.sendKeys(searchValue);
			break;

		case "Demand Response Customer Number" :
			demandResponseRadio.click();
			drCustomerNo.sendKeys(searchValue);
			break;

		case "Demand Response Vendor Id" :
			demandResponseRadio.click();
			drVendorId.sendKeys(searchValue);
			break;
		}

		search.click();
	}
}
