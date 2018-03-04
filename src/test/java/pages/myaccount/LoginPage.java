package pages.myaccount;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import pages.PageSupporter;
/**
 * 
 * @author         :VNatarajan
 * @since          :Sep 20, 2017
 * @filename       :LoginPage.java
 * @version		   :
 * @description    :Page web elements and all functional features of Login page in My Account 2.0
 */
public class LoginPage extends PageSupporter{
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	@FindBy(how=How.ID,using="EmailAddress")
	private WebElement email;

	@FindBy(how=How.ID,using="Password")
	private WebElement password;

	@FindBy(how=How.CSS,using="button[ng-click*='sendForm']")
	private WebElement logIn;

	@FindBy(how=How.CSS,using="li a[href*='LogOff']")
	private WebElement logOut;


	public boolean logIn(String emailData,String passwordData) {
		try {
			Actions action = new Actions(driver);
			waitFor(email,45);
			email.sendKeys(emailData);
			password.sendKeys(passwordData);
			action.moveToElement(logIn).click().build().perform();
			waitFor(logOut,45);
			Assert.assertTrue(logOut.isDisplayed());
			return true;
		}catch(Exception e) {
			return false;
		}
			
	}
}