package support;

import com.jacob.com.LibraryLoader;
import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.enums.StatusAs;
/**
 * 
 * 
 * @author         :VNatarajan
 * @since          :Oct 8, 2017
 * @filename       :ALMConnection.java
 * @version		   :1.0
 * @description    :
 */
public class ALMConnection {
	private static ALMConnection almConnection;
	private static Configurations configurations = Configurations.getInstance();
	static ALMServiceWrapper almWrapper;
	
	/**
	 * 
	 * Method Name : getInstance
	 * Return Type : ALMConnection
	 * Author      : VNatarajan
	 * Date		   : Sep 29, 2017
	 * Description : 
	 * @return
	 */
	public static ALMConnection getInstance(){

		if(almConnection==null){

			String almURL = configurations.getProperty("almURL"); 
			String almUserName = configurations.getProperty("almUserName");
			String almPassword = configurations.getProperty("almPassword");
			String domain = configurations.getProperty("domain");
			String almProject = configurations.getProperty("almProject");
			String jacobDllPath = configurations.getProperty("jacobDllPath");
			try {
				almConnection= new ALMConnection();
				System.setProperty("jacob.dll.path",jacobDllPath);
				LibraryLoader.loadJacobLibrary();
				almWrapper = new ALMServiceWrapper(almURL);
				almWrapper.connect(almUserName,almPassword, domain, almProject);
			}catch (Exception e) {
				e.printStackTrace();
				return almConnection;
			}

		}
		return almConnection;
	}

	/**
	 * 
	 * Method Name : updateResultsInALM
	 * Return Type : void
	 * Author      : VNatarajan
	 * Date		   : Sep 29, 2017
	 * Description : 
	 * @param testcaseName
	 * @param status
	 */
	public synchronized void updateResultsInALM(String testcaseName,StatusAs status) {

		String testSetPath = configurations.getProperty("testSetPath"); 
		String testSetName = configurations.getProperty("testSetName");
		int testSetId = Integer.parseInt(configurations.getProperty("testSetId"));
		try {
			almWrapper.updateResult(testSetPath,testSetName, testSetId, testcaseName, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean closeALMConnectionInstance() {
		if(almWrapper!=null) {
			almWrapper.close();
			return true;
		}
		return false;
	}
}

