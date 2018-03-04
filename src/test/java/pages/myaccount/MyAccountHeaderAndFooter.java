package pages.myaccount;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import junit.framework.Assert;
import pages.PageSupporter;
/**
 * 
 * @author         :VNatarajan
 * @since          :Sep 20, 2017
 * @filename       :HeaderAndFooter.java
 * @version		   :
 * @description    :Page web elements and all functional features of Header and Footer page in My Account 2.0
 */
public class MyAccountHeaderAndFooter extends PageSupporter{
	public MyAccountHeaderAndFooter(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	@FindBy(how=How.CSS,using="button[data-id='selCustomer']")
	private WebElement customersList;

	@FindBy(how=How.LINK_TEXT,using="Home")
	private WebElement home;

	@FindBy(how=How.CSS,using="[ng-model*='accountSelection'] button")
	private WebElement accountsList;

	@FindBy(how=How.CSS,using="[ng-model*='customerSelection'] button")
	private WebElement customerList;

	@FindBy(how=How.PARTIAL_LINK_TEXT,using="All Accounts")
	private WebElement accountSelection;

	@FindBy(how=How.CSS,using="div[ng-model*='accountSelection'] [ng-model*='filterText']")
	private WebElement filterText;

	@FindBy(how=How.CSS,using="div[ng-model*='customerSelection'] [ng-model*='filterText']")
	private WebElement filterTextCustomer;

	@FindBy(how=How.CSS,using="div[ng-model='customer'] h3")
	private WebElement customerHeaderText;

	@FindAll(value = { @FindBy(how=How.CSS,using="div[ng-show*='CustomerProfiles.length==1']:not([class*='ng-hide'])") })
	private List<WebElement> singleCustomerDropdown;

	@FindBy(how=How.CSS,using="span[ng-if*='singleAccount']")
	private List<WebElement> singleAccountDropdown;

	@FindBy(how=How.PARTIAL_LINK_TEXT,using="Log Out")
	private WebElement logOut;

	@FindAll(value = { @FindBy(how=How.CSS,using="button[ng-click*='ok']") })
	private List<WebElement> OK;

	public void selectAnAccount(String accountNo) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);
		if(singleAccountDropdown.size()>0) {
			Assert.assertTrue(singleAccountDropdown.get(0).isDisplayed());
			return;
		}
		By locator = By.xpath("//*[contains(text(),'"+accountNo+"')]");
		waitFor(home,30);
		home.click();
		waitFor(accountsList,15);
		accountsList.click();
		js.executeScript("scroll(0, 450);");
		filterText.clear();
		filterText.sendKeys(accountNo.trim());
		waitUntil(locator,15);
		WebElement account = driver.findElement(locator);
		action.moveToElement(account).click().perform();
		wait(3);
		Assert.assertTrue(customerHeaderText.isDisplayed());
	}

	public void selectACustomer(String customerName) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);
		if(singleCustomerDropdown.size()>0) {
			Assert.assertTrue(singleCustomerDropdown.get(0).isDisplayed());
			return;
		}
		By locator = By.xpath("//*[contains(text(),'"+customerName+"')]");
		waitFor(home,30);
		home.click();
		waitFor(customerList,15);
		customerList.click();
		js.executeScript("scroll(0, 450);");
		filterTextCustomer.clear();
		filterTextCustomer.sendKeys(customerName.trim());
		waitUntil(locator,15);
		WebElement account = driver.findElement(locator);
		action.moveToElement(account).click().perform();
		wait(3);
		Assert.assertTrue(customerHeaderText.isDisplayed());
	}
	
	public void selectAHistoricalAccount(String accountNo) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		By locator = By.xpath("//a[contains(text(),'No accounts found')]");
		waitFor(home,30);
		home.click();
		waitFor(accountsList,15);
		accountsList.click();
		js.executeScript("scroll(0, 450);");
		filterText.sendKeys(accountNo.trim());
		waitUntil(locator,15);
		WebElement account = driver.findElement(locator);
		Assert.assertTrue(account.isDisplayed());
		wait(3);
	}
	
	public boolean selectSubMenuFromMenu(String menu1,String submenu1) {
		try {
			Actions action = new Actions(driver);
			By locator = By.partialLinkText(menu1);
			waitUntil(locator,30);
			WebElement menu = driver.findElement(locator);
			//menu.click();
			action.moveToElement(menu).click().perform();
			WebElement submenu;
			if(!submenu1.equals("Admin")) {
				submenu = driver.findElement(By.partialLinkText(submenu1));
			}else {
				submenu = driver.findElements(By.partialLinkText(submenu1)).get(1);
			}
			waitFor(submenu,15);
			action.moveToElement(submenu).click().perform();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void customerSelectionInHeader(String customerSelection) {
		Actions action = new Actions(driver);
		By customer = By.xpath("//span[contains(text(),'"+customerSelection+"')]");
		waitFor(customersList,45);
		customersList.click();
		waitUntil(customer,5);
		action.moveToElement(driver.findElement(customer)).click().perform();
		wait(3);
	}

	public void accountSelectionInHeader(String accountSelection) {
		Actions action = new Actions(driver);
		By account = By.xpath("//span[contains(text(),'"+accountSelection+"')]");
		waitFor(accountsList,45);
		accountsList.click();
		waitUntil(account,5);
		action.moveToElement(driver.findElement(account)).click().perform();
		wait(3);
	}

	public void customerAndAccountSelectionInHeader(String customerSelection,String accountSelection) {
		customerSelectionInHeader(customerSelection);
		accountSelectionInHeader(accountSelection);
	}

	public void logOutFromMyAccount() {
		waitFor(logOut,45);
		logOut.click();
		wait(3);
		if(OK.size()>0) {
			OK.get(0).click();
		}
	}
}
