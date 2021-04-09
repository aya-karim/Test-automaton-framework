package TestCases;

import org.openqa.selenium.WebDriver;

import dataAccess.XMLDataReader;
import seleniumInterfacing.Actions;

/**
 * @author akarim Template Test Case Interface to Define a format for the Test
 *         case using template Design Pattern
 */
public abstract class TestCaseTemplate {
	protected abstract void setup() throws Exception;

	protected abstract void execution() throws Exception, Throwable;

	protected abstract void cleanup() throws Exception;

	WebDriver webDriver;
	XMLDataReader readerInstance;
	Actions handler;
	int Steps;

	/**
	 * The Function that Determines the Sequence of the methods to be implemented
	 * 
	 * @throws Throwable
	 */
	public void run() throws Throwable {
		setup();
		execution();
		cleanup();
	}
}
