package pages.smb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.PageSupporter;

public class HomePage extends PageSupporter{

	WebDriver driver;
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	@FindBy(how=How.ID,using="zipCode")
	private WebElement zip;

	@FindBy(how=How.CSS,using="a[onclick*='updateZip']")
	private WebElement updateZip;

	@FindBy(how=How.ID,using="pencil")
	private WebElement changeLocation;
	
	/**
	 * 
	 * @param zip1
	 */
	public void updateZipAndUtility(String zip1){
		waitFor(changeLocation,45);
		changeLocation.click();
		zip.clear();
		zip.sendKeys(zip1);
		waitFor(updateZip,30);
		updateZip.click();
	}
}