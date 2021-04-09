package seleniumInterfacing;

/** 
* @author akarim 
* This is the Main interface to implement the Selenium test Steps
* takes the Web elements and data as parameter and Execute the commands on them
*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Actions {
//	WebDriver driver;
	/**
	 * Enum to identify which browser Driver to be opened
	 */
	public static enum FindBy {
		ID, xPath, className, Name;
	}

	String webDriver;

	/**
	 * Constructor of Actions to Open web driver and maximize it
	 */
	public Actions(WebDriver driver) throws Exception {
		driver.manage().window().maximize();

	}

	/**
	 * Takes the WebDriver and the needed URL and open it. Throws Exception if it
	 * can't read URL
	 */
	public void openBrowser(WebDriver driver, String url) {
		driver.get(url);
	}

	/**
	 * Clicks on the Web Element
	 * 
	 * @param WebDriver , identifier, String Takes the WebDriver, the identifier you
	 *                  want to find the element by and the Data to be found throws
	 *                  Exception if the Data is not found
	 */
	public void click(WebDriver driver, FindBy identifier, String value) {
		driver.findElement(identify(identifier, value)).click();
	}

	/**
	 * send KeyWord to input Attribute
	 * 
	 * @param WebDriver , identifier, String, String Takes the WebDriver, the
	 *                  identifier you want to find the element by , the Data to be
	 *                  found and the Key word to be sent to input throws Exception
	 *                  if the Data is not found
	 */
	public void type(WebDriver driver, FindBy identifier, String value, String text) {
		driver.findElement(identify(identifier, value)).sendKeys(text);
	}

	/**
	 * Get text of WebElement
	 * 
	 * @param WebDriver , identifier, String Takes the WebDriver, the identifier you
	 *                  want to find the element by , the Data to be found and the
	 *                  Key word to be sent to input throws Exception if the Data is
	 *                  not found
	 */
	public String gettext(WebDriver driver, FindBy identifier, String value) {
		return driver.findElement(identify(identifier, value)).getText();
	}

	/**
	 * Get size of the WebElement
	 * 
	 * @param WebDriver , identifier, String Takes the WebDriver, the identifier you
	 *                  want to find the element by , the Data to be found and the
	 *                  Key word to be sent to input throws Exception if the Data is
	 *                  not found
	 */
	public int getelemntsize(WebDriver driver, FindBy identifier, String value) {
		return driver.findElements(identify(identifier, value)).size();
	}

	/**
	 * Wait for Element to be visible on web Page
	 * 
	 * @param WebDriver , identifier, String Takes the WebDriver, the identifier you
	 *                  want to find the element by , the Data to be found and the
	 *                  Key word to be sent to input throws Exception if the Data is
	 *                  not found
	 */
	public void waitforexist(WebDriver driver, FindBy identifier, String value, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(identify(identifier, value)));
	}

	/**
	 * Submit the Web element
	 * 
	 * @param WebDriver , identifier, String Takes the WebDriver, the identifier you
	 *                  want to find the element by , the Data to be found and the
	 *                  Key word to be sent to input throws Exception if the Data is
	 *                  not found
	 */
	public void submit(WebDriver driver, FindBy identifier, String value) {
		driver.findElement(identify(identifier, value)).submit();
	}

	/**
	 * gets the identifier of the needed Data from XML Tag Declared with
	 * 
	 * @param identifier, String Takes the WebDriver, the identifier you want to
	 *                    find the element by , the Data to be found and the Key
	 *                    word to be sent to input throws Exception if the Data is
	 *                    not found
	 */
	public By identify(FindBy identification, String value) {
		switch (identification) {
		case ID:
			return By.id(value);
		case xPath:
			return By.xpath(value);
		case className:
			return By.className(value);
		case Name:
			return By.name(value);
		default:
			return null;
		}
	}

}
