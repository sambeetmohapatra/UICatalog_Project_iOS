/**
 * 
 */
package ios.scripts;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import ios.common.functions.Base_Class;
import ios.pageObjects.Get_WebElement;

/**
 * @author sambeetmohapatra
 *
 */
public class Test_05_UI_Catalog_Buttons extends Base_Class {

	@Test(invocationCount=1)
	public void test_05_UI_Catalog_Buttons() throws Exception{
		
// Initialization
		
	Get_WebElement element = new Get_WebElement();
			
//Step 1 : Verify App is launched Successfully and HomePage is displayed  
	logger.log(LogStatus.INFO,"Step 1 : Verify App is launched Successfully an HomePage is displayed ");
	WebElement Buttons_id = waitForElement(element.getElementByAccessibilityId("Buttons_id"));
	Assert.assertTrue(isElementDisplayed(Buttons_id));
	logger.log(LogStatus.PASS, "Actual : " + showText(Buttons_id));
	
//Step 2 : Click on Buttons label and Validate the Elements Displayed
	logger.log(LogStatus.INFO,"Step 2 : Click on Buttons label and Validate the Elements Displayed");
	click(Buttons_id);
	takeScreenshot();
	Assert.assertTrue(isElementDisplayed(Buttons_id));
	Assert.assertTrue(isElementDisplayed(waitForElement(element.getElementByXpath("System_Text_label_xpath"))));
	Assert.assertTrue(isElementDisplayed(waitForElement(element.getElementByXpath("Button_xpath"))));
	Assert.assertTrue(isElementDisplayed(waitForElement(element.getElementByXpath("System_Contact_Add_label_xpath"))));
	Assert.assertTrue(isElementDisplayed(waitForElement(element.getElementByAccessibilityId("System_disclosure_id"))));
	Assert.assertTrue(isElementDisplayed(waitForElement(element.getElementByAccessibilityId("More_Info_label_id"))));
	Assert.assertTrue(isElementDisplayed(waitForElement(element.getElementByAccessibilityId("XButton_id"))));
	Assert.assertTrue(isElementDisplayed(waitForElement(element.getElementByAccessibilityId("Image_label_id"))));
	Assert.assertTrue(isElementDisplayed(waitForElement(element.getElementByAccessibilityId("Attributed_Text_id"))));

	logger.log(LogStatus.PASS, "Validated the Elements in Buttons Screen");

//Step 3: Swipe To the Bottom of the Screen 
	logger.log(LogStatus.INFO,"Step 3 : Swipe To the Bottom of the Screen");
	swipeDown_Vertical(2);
	
//Step 4 : Go Back to Home Screen 
	logger.log(LogStatus.INFO, "Step 4 : Go Back to Home Screen ");
	goBack();

	}

}
