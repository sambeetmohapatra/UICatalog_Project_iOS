/**
 * 
 */
package ios.common.functions;

/**
 * @author sambeetmohapatra
 *
 */
public interface Automation_Constants {
	
	//String iOSDevice="iPad"; //********  Use iPad Air 2
	String iOSDevice="iPhone"; //***** Use iPhone 6

	
	int TimeOut =20; // Waiting period Timeout
	String NAME_ATTRIBUTE="name";
	String LABEL_ATTRIBUTE="label";
	String VALUE_ATTRIBUTE="value";

	String APPIUM_SERVER_URL="http://127.0.0.1:4723/wd/hub";

	String TEST_DATA_FILE="./src/test/resources/TestData.xls";
	String REPORT_PATH="/Users/sambeetmohapatra/Desktop/Sambeet_Work/UI_Catalog_Project/Reports/";
	String SCREENSHOT_PATH="/Users/sambeetmohapatra/Desktop/Sambeet_Work/UI_Catalog_Project/ScreenShots/";
	String ERROR_SCREENSHOT_PATH = "/Users/sambeetmohapatra/Desktop/Sambeet_Work/UI_Catalog_Project/Error_Screenshots/";
	String EXTENT_CONFIG_FILE="./src/test/resources/extent_config.xml";
	String PROPERTIES_FILE="./src/test/resources/App_Settings.properties";
	String PAGE_OBJECTS_JSON_FILE="./src/test/resources/json_page_objects.json";
	String EXCEL_SHEET_UI_CATALOG="Ui_Catalog_Data";
	
	String WebUrl = "http://automationpractice.com/index.php";
	String Native_App_View ="NATIVE_APP";
	String Web_App_View = "WEBVIEW";
}
