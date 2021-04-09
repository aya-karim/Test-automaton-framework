package seleniumInterfacing;

import javax.management.RuntimeErrorException;

/** 
 * @author akarim
 * Initialize the Web Driver to be used by user
*/
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import seleniumInterfacing.CreateWebDriver.BrowserTypes;

public class CreateWebDriver {
	public static enum BrowserTypes {
		Chrome, Firefox, IE
	}

	/**
	 * Constructor takes the Enum as parameter to choose which Web Driver to be
	 * used.
	 */
	public WebDriver createDriver(String browser) {
		BrowserTypes type;
		
		try {
			type = BrowserTypes.valueOf(browser);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(
					"Input browser not supported. Refer to ReadMe file for valid values. Aborting test case");
		}

		switch (type) {
		case Chrome:
			return createChromeDriver();
		case Firefox:
			return createFirefoxDriver();
		case IE:
			return createIEDriver();
		default:
			System.err.println("invalid browser");
			return null;
		}
	}

	/**
	 * Creates Chrome Web Driver
	 */
	private WebDriver createChromeDriver() {
		System.setProperty("webdriver.chrome.driver", ".//src//main//resources//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	/**
	 * Creates FireFox Web Driver
	 */
	private WebDriver createFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", ".//src//main//resources//geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	/**
	 * Created Internet Explorer Web Driver
	 */
	private WebDriver createIEDriver() {
		System.setProperty("webdriver.ie.driver", ".//src//main//resources//IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		return driver;
	}

}
