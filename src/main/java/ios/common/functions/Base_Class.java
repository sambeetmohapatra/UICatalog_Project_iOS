/**
 * 
 */
package ios.common.functions;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.ios.IOSDriver;

/**
 * @author sambeetmohapatra
 *
 */
public class Base_Class extends Utility {

	private DesiredCapabilities caps;
	private Process process;
	public static ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void before_Suite() throws Exception{
		Reporter.log(getFormatedDateTime()+" ***** Suite Execution Started *****",true);
		
		report = new ExtentReports(REPORT_PATH+getFormatedDateTime()+".html");
		report.loadConfig(new File(EXTENT_CONFIG_FILE));
	//	startServer();
		Launch_App();
	}
	
	@BeforeMethod
	public void before_Method() throws Exception{
			logger=report.startTest(this.getClass().getSimpleName());
			Reporter.log("********************* "+this.getClass().getName().trim().toUpperCase()+" *********************",true);
			
	}
	
	@AfterMethod
	public void after_Method(ITestResult result) {
		try{
		 if(result.getStatus() == ITestResult.FAILURE)
	        {
	            String screenShotPath = Screenshot.getErrorCapture("Failed_TC_"+result.getMethod().getMethodName());
	            logger.log(LogStatus.FAIL, result.getThrowable());
	            logger.log(LogStatus.FAIL, "Snapshot below: " + logger.addScreenCapture(screenShotPath));
	            goBack();
	        }
		}
		catch(Exception e){
			System.out.println(e);
		}
		//Close App
		
		
		logger.log(LogStatus.PASS, " End of Test Case :  Device Used : " +iOSDevice+" ; " +result.getMethod().getMethodName().toUpperCase()+" ; Closed Application");
		Reporter.log(getFormatedDateTime()+"***************** Execution Complete : " +result.getMethod().getMethodName().toUpperCase()+" ******************",true);
		//End of Test Case - Method - Extent Reports
		report.endTest(logger);
	}

	@AfterSuite
	public void after_Suite(){
		driver.quit();
		Reporter.log(getFormatedDateTime()+" App Closed ",true);
		
		//stopServer();
		// Add the Extent Reports
		report.flush();
		report.close();
		Reporter.log(getFormatedDateTime()+"*** "+iOSDevice+" ; " +" ***** Suite Execution Completed *****",true);
	}
	
						/*
						 * ********************************************************
						 * ****************  REUSABLE METHODS  ********************
						 */
	
	//Take Screenshot and add it to Extent Reports also
	public void takeScreenshot(){
		Wait(1);
		Reporter.log(getFormatedDateTime()+" Capturing Screenshot and Adding to Extent Reports",true);
        logger.log(LogStatus.INFO, logger.addScreenCapture( new Screenshot().capture(this.getClass().getName())));
	}
	//Start Appium Server Method -Mac
	public void startServer() throws InterruptedException, IOException{
		String[] command = { "/usr/bin/killall", "-9", "node" };
		Runtime.getRuntime().exec(command);
		String[] commandProxy = { "/usr/bin/killall", "-9", "ios_webkit_debug_proxy" };
		Runtime.getRuntime().exec(commandProxy);
		//Start Appium Server 
		
		String start_server = "/usr/local/bin/node /usr/local/bin/appium";
		process = Runtime.getRuntime().exec(start_server);
		Wait(10);
		/*
			Reporter.log("Start Execution",true);
			  process = Runtime.getRuntime().exec("/usr/bin/open -a /Applications/Utilities/Terminal.app /bin/bash /usr/local/bin/appium");
			    process.waitFor();	
			    Wait(8);*/
		
		if(process!=null)
			System.out.println(" Started Appium Server");
		else
			System.out.println(" Unable to launch Appium Server");
	}
	//Stop Appium Server Method -Mac
	public void stopServer(){
		
		process.destroy();
		//kill appium node after end of your execution
		String[] command = { "/usr/bin/killall", "-9", "node" };
		try {
		Runtime.getRuntime().exec(command);
		System.out.println("Appium server stopped.");
		} catch (IOException e) {
		e.printStackTrace();
		}
		//Kill webkit proxy for iOS
		String[] commandProxy = { "/usr/bin/killall", "-9", "ios_webkit_debug_proxy" };
		try {
		Runtime.getRuntime().exec(commandProxy);
		System.out.println("iOS Webkit proxy stopped");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	//Launch Application on iPhone 6 Simulator - iOS 10.3
	public void Launch_App() throws Exception{
		if(iOSDevice.equalsIgnoreCase("iPhone")) {
		try {
		//Set Desired Capabilities
		Reporter.log(getFormatedDateTime()+" Set Desired Capabilities - iOS : "+iOSDevice,true);
				
			    caps = new DesiredCapabilities();
				caps.setCapability("platformName", getProperty("platformName", PROPERTIES_FILE));
				caps.setCapability("platformVersion", getProperty("platformVersion", PROPERTIES_FILE));
				caps.setCapability("deviceName", getProperty("deviceName", PROPERTIES_FILE));
				caps.setCapability("noReset", true);
		//Set Application Capabilities		
				
				caps.setCapability("bundleId",getProperty("bundleId", PROPERTIES_FILE));
				//caps.setCapability("app", getProperty("app_path", PROPERTIES_FILE));
		// Launch iOSDriver
				
				Reporter.log(getFormatedDateTime()+" Launch iOSDriver : "+caps,true);
				
				driver = new IOSDriver<WebElement>(new URL(APPIUM_SERVER_URL), caps);
				driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
		}
		catch (Exception e) {
			Reporter.log("Please Start/Restart Appium Server , Not Starting it Programatically , Please Open iPhone Simulator",true);
		}
		}
	
	
	else if(iOSDevice.equalsIgnoreCase("iPad")) {
		try {
		//Set Desired Capabilities
				Reporter.log(getFormatedDateTime()+" Set Desired Capabilities - iOS : " +iOSDevice,true);
				
				  caps = new DesiredCapabilities();
					caps.setCapability("platformName", getProperty("platformName", PROPERTIES_FILE));
					caps.setCapability("platformVersion", getProperty("platformVersion", PROPERTIES_FILE));
					caps.setCapability("deviceName",getProperty("deviceName_iPad", PROPERTIES_FILE));
					caps.setCapability("noReset", true);
			//Set Application Capabilities		
					
					caps.setCapability("bundleId",getProperty("bundleId_iPad", PROPERTIES_FILE));
			// Launch iOSDriver
					
					Reporter.log(getFormatedDateTime()+" Launch iOSDriver : "+caps,true);
					
					driver = new IOSDriver<WebElement>(new URL(APPIUM_SERVER_URL), caps);
					driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
					//Rotate iPad to Landscape view for the test
					Reporter.log(getFormatedDateTime()+" Rotate iPad to Landscape view for the test",true);

					RotateApp(getProperty("Rotate_iPad", PROPERTIES_FILE));
			}
			catch (Exception e) {
				Reporter.log("Please Open iPad Simulator",true);
			}
			}
	}

		
}
