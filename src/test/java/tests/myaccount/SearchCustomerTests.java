package tests.myaccount;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import com.aventstack.extentreports.Status;

import tests.TestSupporter;
/**
 * 
 * @author         :VNatarajan
 * @since          :Sep 13, 2017
 * @filename       :SearchCustomerTests.java
 * @version		   :
 * @description    :
 */
@Listeners(VideoListener.class)
public class SearchCustomerTests extends TestSupporter{
	

	/**
	 * 
	 * Method Name : searchCustomersUsingDemandResponseCustomer
	 * Return Type : void
	 * Author      : VNatarajan
	 * Date		   : Sep 13, 2017
	 * Description : This method is to test Search customer using Admin user with Demand Response Customer as search criteria
	 */
	
	@Video
	@Test(description="Admin User - Search Customers using Demand Response Customer")
	public void searchCustomersUsingDemandResponseCustomer() {

		String email = data.get("email");
		String password = data.get("password");
		String searchCriteria = data.get("searchCriteria");
		String searchValue  = data.get("searchValue");
		loginPage.logIn(email, password);
		test.log(Status.PASS, "Login to My Account is successful");	
		myAccountHeaderAndFooter.selectSubMenuFromMenu("Admin", "Search Customers");
		test.log(Status.INFO, "Navigation to Search customers screen through Admin...",takeScreenshot());
		adminPage.searchCustomersInAdmin(searchCriteria, searchValue);
		test.log(Status.PASS, "The customer is searched successfully using search criteria: "+searchCriteria+" and search value : "+searchValue,takeScreenshot());
	}

	/**
	 * 
	 * Method Name : searchCustomersUsingDemandResponseVendorId
	 * Return Type : void
	 * Author      : VNatarajan
	 * Date		   : Sep 13, 2017
	 * Description : This method is to test Search customer using Admin user with Demand Response Vendor Id as search criteria
	 */
	
	@Video
	@Test(description="Admin User - Search Customers using Demand Response Vendor Id")
	public void searchCustomersUsingDemandResponseVendorId() {

		String email = data.get("email");
		String password = data.get("password");
		String searchCriteria = data.get("searchCriteria");
		String searchValue  = data.get("searchValue");
		loginPage.logIn(email, password);
		test.log(Status.PASS, "Login to My Account is successful");	
		myAccountHeaderAndFooter.selectSubMenuFromMenu("Admin", "Search Customers");
		test.log(Status.INFO, "Navigation to Search customers screen through Admin...",takeScreenshot());
		adminPage.searchCustomersInAdmin(searchCriteria, searchValue);
		test.log(Status.PASS, "The customer is searched successfully using search criteria: "+searchCriteria+" and search value : "+searchValue,takeScreenshot());
		
	}

}
