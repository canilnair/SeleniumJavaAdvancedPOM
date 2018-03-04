package tests;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.paulhammant.ngwebdriver.NgWebDriver;
import atu.alm.wrapper.enums.StatusAs;
import pages.myaccount.AdminPage;
import pages.myaccount.LoginPage;
import pages.myaccount.MyAccountHeaderAndFooter;
import pages.smb.CompanyAndLocationDetailsPage;
import pages.smb.EnergyRatesPage;
import pages.smb.HomePage;
import pages.smb.ReviewAndConfirmSignUpPage;
import pages.smb.SMBheaderAndFooter;
import support.ALMConnection;
import support.Configurations;
import support.DataReader;
import support.DataReaderEXCEL;
import support.DataReaderXML;
import support.Report;
import support.Settings;
import support.UiDriver;


public class TestSupporter {
	private Settings settings = Settings.getInstance();
	String dataSourcePath = settings.getDataSource();
	String dataXmlSourcePath = settings.getXMLDataSource();
	String driversPath = settings.getDriverEXEDir();
	String screenshotsPath = settings.getScreenshotsDir();
	Configurations configurations = Configurations.getInstance();
	protected Report report = Report.getInstance();

	protected UiDriver uidriver = new UiDriver();
	protected DataReader data;
	protected WebDriver driver;
	NgWebDriver ngDriver;
	protected ExtentTest test;
	
	protected MyAccountHeaderAndFooter myAccountHeaderAndFooter;
	protected LoginPage loginPage;
	protected AdminPage adminPage;

	protected CompanyAndLocationDetailsPage companyAndLocationDetailsPage;
	protected EnergyRatesPage energyRatesPage;
	protected SMBheaderAndFooter smbheaderAndFooter;
	protected HomePage homePage;
	protected ReviewAndConfirmSignUpPage reviewAndConfirmSignUpPage;
	
	/**
	 * Method name  : setUpTest
	 * Return types : void
	 * Description  : Set up the tests
	 */

	@Parameters({"Browser","URL","Datasource","SheetName"})
	@BeforeMethod(alwaysRun=true)
	public void initializeBrowser( @Optional("Google Chrome")String browserName,String url,String datasource,String sheetName,Method method,ITestContext context) {
		String dataSource = configurations.getProperty("DataSource");
		String testId;
		String testName;
		Test testMethod = method.getAnnotation(Test.class);
		if (testMethod.description().length()>0) {
			testName = testMethod.description().trim();
		}else {
			testName = "Test description is not given";
		}

		if(dataSource.equalsIgnoreCase("excel")) {
			data = new DataReaderEXCEL(dataSourcePath+datasource,sheetName,testName);
			testId = data.get("Test_ID");	
		}else {
			data = new DataReaderXML(dataXmlSourcePath+"SearchCustomerTests.xml",testName);
			testId = data.getTestId();
		}
		
		String testDescription = context.getCurrentXmlTest().getName();
		test = report.createNewReport(testId,testDescription+" - "+testName);
		//Instantiate the driver instance
		driver = uidriver.getDriver(browserName);
		//Initialize the pages	
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		myAccountHeaderAndFooter = PageFactory.initElements(driver, MyAccountHeaderAndFooter.class);
		adminPage = PageFactory.initElements(driver, AdminPage.class);

		
		companyAndLocationDetailsPage = PageFactory.initElements(driver, CompanyAndLocationDetailsPage.class);
		energyRatesPage = PageFactory.initElements(driver, EnergyRatesPage.class);
		smbheaderAndFooter = PageFactory.initElements(driver, SMBheaderAndFooter.class);
		homePage = PageFactory.initElements(driver, HomePage.class);
		reviewAndConfirmSignUpPage = PageFactory.initElements(driver, ReviewAndConfirmSignUpPage.class);
		
		//browser initial set ups
		//driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(600, TimeUnit.SECONDS);
		driver.get(url);
	}

	/**
	 * 
	 * Method Name : cleanUp
	 * Return Type : void
	 * Author      : VNatarajan
	 * Date		   : Sep 29, 2017
	 * Description : 
	 * @param result
	 * @param method
	 */
	@AfterMethod(alwaysRun=true)
	public void cleanUp(ITestResult result,Method method) {
		String testName;

		Test testMethod = method.getAnnotation(Test.class);
		if (testMethod.description().length()>0) {
			testName = testMethod.description().trim();

		}else {
			testName = "Test description is not given";
		}
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				test.log(Status.FAIL, "Exception occurred in the test -  Error Detail : "+result.getThrowable().getMessage(),takeScreenshot());
				updateALMStatus(testName,StatusAs.FAILED);
			}else {
				updateALMStatus(testName,StatusAs.PASSED);
			}
			//Close and quite driver instance
			driver.quit();
		}catch(Exception e) {
			test.log(Status.FAIL, "Exception occurred in the test -  Error Detail : "+result.getThrowable().getMessage(),takeScreenshot());
			updateALMStatus(testName,StatusAs.FAILED);
		}
		//Close report
		report.closeReport();
	}
	
	/**
	 * 
	 * @param testName
	 * @param status
	 */
	private void updateALMStatus(String testName,StatusAs status) {
		if(configurations.getProperty("UpdateALM").equalsIgnoreCase("yes")) {
			ALMConnection alm = ALMConnection.getInstance();
			alm.updateResultsInALM(testName, status);	
		}
	}


	/**
	 * 
	 * Method Name : captureScreen
	 * Return Type : String
	 * Author      : VNatarajan
	 * Date		   : Sep 29, 2017
	 * Description : 
	 * @return
	 */
	public String captureScreen() {
		try {
			Random rand = new Random();
			int  randomNo = rand.nextInt(1000) + 1;
			//WebDriver augmentedDriver = new Augmenter().augment(driver); 
			File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String screenShotName = screenshotsPath+"screenshot_"+randomNo+".png";
			FileUtils.copyFile(source, new File(screenShotName));
			return screenShotName;
		}
		catch(Exception e) {
			return "ERROR_IN_SCREEN_CAPTURE";
		}
	}

	/**
	 * 
	 * Method Name : takeScreenshot
	 * Return Type : MediaEntityModelProvider
	 * Author      : VNatarajan
	 * Date		   : Sep 29, 2017
	 * Description : 
	 * @return
	 */
	public  MediaEntityModelProvider takeScreenshot() {
		try {
			return MediaEntityBuilder.createScreenCaptureFromPath(captureScreen()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * Method Name : takeExecutionHistory
	 * Return Type : void
	 * Author      : VNatarajan
	 * Date		   : Sep 29, 2017
	 * Description :
	 */
	@AfterSuite
	public void testsTearDown(){
		String reportPath=settings.getProjectPath();		
		String history = System.getProperty("user.dir")+System.getProperty("file.separator")+"History"+System.getProperty("file.separator");
		new File("history").mkdirs();
		File historyDir = new File(history);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.contains("Execution_");
			}
		};
		String[] resultsDir = historyDir.list(filter);
		int executionDirIndex = resultsDir.length+1;
		String executionDir = history+System.getProperty("file.separator")+"Execution_"+executionDirIndex;
		new File(executionDir).mkdirs();
		try {
			FileUtils.copyFileToDirectory(new File(reportPath+System.getProperty("file.separator")+"TestSummaryReport.html"), new File(executionDir),false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ALMConnection.getInstance().closeALMConnectionInstance();
	}
}
