package pages.smb;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import pages.PageSupporter;
/**
 * 
 * 
 * @author         :VNatarajan
 * @since          :Oct 9, 2017
 * @filename       :ReviewAndConfirmSignUpPage.java
 * @version		   :1.0
 * @description    :
 */
public class ReviewAndConfirmSignUpPage extends PageSupporter{

	WebDriver driver;
	public ReviewAndConfirmSignUpPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	@FindBy(how=How.CSS,using="i[ng-class*='agreeTnCE']")
	private WebElement termsAndConditions;

	@FindBy(how=How.NAME,using="ReviewAndConfirm")
	private WebElement reviewAndConfirm;

	@FindBy(how=How.ID,using="rconfirm")
	private WebElement confirmAndSignUp;
	
	@FindAll(value = { @FindBy(how=How.CSS,using="div.col-md-8.col-md-offset-2 h3.my-bold") })
	private List<WebElement> successMessage;

	@FindBy(how=How.CSS,using="p.my-bold.text-16")
	private WebElement orderNumber;
/**
 * 
 */
	public void reviewAndConfirmSignUp(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		waitFor(termsAndConditions,120);
		js.executeScript("arguments[0].click();",termsAndConditions);
		waitFor(confirmAndSignUp,30);
		wait(2);
		js.executeScript("arguments[0].click();",confirmAndSignUp);
	}

	public void validateSignUpConfirmation(){
		String OrderNo;
		waitFor(orderNumber,120);
		OrderNo = orderNumber.getText();
		Assert.assertTrue(OrderNo.length()>0);
	}
}
