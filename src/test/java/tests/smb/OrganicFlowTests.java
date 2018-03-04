package tests.smb;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import tests.TestSupporter;

public class OrganicFlowTests extends TestSupporter{

	@Test(description="Organic Flow test for SMB Enrollment")
	public void organicEnrollmentForSMB() {

		String zip = data.get("Zip");
		String companyName = data.get("CompanyName");
		String firstName = data.get("FirstName");
		String lastName  = data.get("LastName");
		String email  = data.get("Email");
		String phoneNumber  = data.get("PhoneNumber");
		String street  = data.get("Street");
		String unit  = data.get("Unit");
		String city  = data.get("City");
		String eAccountNo = "33333"+System.currentTimeMillis();;
		homePage.updateZipAndUtility(zip);
		test.log(Status.INFO, "Updated the zip and utility for SMB enrollment...",takeScreenshot());
		smbheaderAndFooter.selectMenuAndSubmenu("SMALL BUSINESS", "Electricity Rates");
		test.log(Status.INFO, "Selecting Electricity from the menu - Small Business.",takeScreenshot());
		energyRatesPage.selectEnergyPlanAndClickSignUp();
		test.log(Status.INFO, "Selecting the energy plan and sign up",takeScreenshot());
		companyAndLocationDetailsPage.fillInSecurityQuestions("City or town of birth", "Dallas");
		test.log(Status.INFO, "Filling in the security questions and answers",takeScreenshot());
		companyAndLocationDetailsPage.fillInCompanyInfo(companyName, firstName, lastName, email, phoneNumber);
		test.log(Status.INFO, "Filling in the company information such as company name,first name,last name and email with phone number",takeScreenshot());
		companyAndLocationDetailsPage.fillInServiceDetails(street, unit, city, eAccountNo);
		test.log(Status.INFO, "Filling in the service details such as street,unit,city and account number",takeScreenshot());
		companyAndLocationDetailsPage.submitReviewAndConfirm();
		test.log(Status.INFO, "Submitting the enrollment for review",takeScreenshot());
		reviewAndConfirmSignUpPage.reviewAndConfirmSignUp();
		test.log(Status.INFO, "Reviewing and confirming the enrollment",takeScreenshot());
		reviewAndConfirmSignUpPage.validateSignUpConfirmation();
		test.log(Status.INFO, "Validate the enrollment after confirmation",takeScreenshot());
	}

}
