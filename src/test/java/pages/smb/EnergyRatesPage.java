package pages.smb;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.PageSupporter;
/**
 * 
 * 
 * @author         :VNatarajan
 * @since          :Oct 9, 2017
 * @filename       :EnergyRatesPage.java
 * @version		   :1.0
 * @description    :
 */
public class EnergyRatesPage extends PageSupporter {
	WebDriver driver;
	public EnergyRatesPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	@FindAll(value = { @FindBy(how=How.CSS,using="button[class*='add-to-cart']") })
	private List<WebElement> addToCart;
	
	@FindAll(value = {@FindBy(how=How.CSS,using="button[value*='ENROLL ONLINE']")})
	private List<WebElement> enrollOnline;
	
	/**
	 * 
	 */
	public void selectEnergyPlanAndClickSignUp(){
		waitFor(enrollOnline.get(0),45);
		enrollOnline.get(0).click();
	}

}
