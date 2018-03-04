package pages.smb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.PageSupporter;
/**
 * 
 * @author VNatarajan
 *
 */
public class SMBheaderAndFooter  extends PageSupporter{
	WebDriver driver;
	public SMBheaderAndFooter(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	@FindBy(how=How.PARTIAL_LINK_TEXT,using="Small Business")
	private WebElement smallBusiness;
/**
 * 
 * @param menu1
 * @param submenu1
 */
	public void selectMenuAndSubmenu(String menu1,String submenu1){
		waitFor(smallBusiness,45);
		Actions action = new Actions(driver);
		WebElement menu = driver.findElement(By.partialLinkText(menu1));
		waitFor(menu,30);
		action.moveToElement(menu).perform();
		wait(2);
		WebElement submenu = driver.findElement(By.partialLinkText(submenu1));
		submenu.click();
	}

}
