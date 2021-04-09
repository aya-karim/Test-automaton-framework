package TestCases;

import static org.junit.Assert.assertTrue;

import org.w3c.dom.Node;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import DTOs.products.Root;
import Report.Report;
import dataAccess.XMLDataReader;
import io.restassured.response.Response;
import restAssuredInterfacing.ApiGadget;

public class GetProduct extends TestCaseTemplate {
	Node nIdentifier;
	Node nData;
	static ExtentTest test;
	static Report report;
	ApiGadget gadget;

	@Override
	protected void setup() throws Exception {
		// TODO Auto-generated method stub
		readerInstance = XMLDataReader.getInstance();
		nIdentifier = readerInstance.getIdentifers();
		report = Report.getInstance();
		test = report.createTestCaseInReport("Get products");
		gadget = new ApiGadget();
		Steps = 0;
	}

	@Override
	protected void execution() throws Throwable {
		// TODO Auto-generated method stub
		gadget.createBaseRequest();
		Response resp = gadget.createRequest("/products");
		Steps++;
		test.log(LogStatus.PASS, Steps + " Create API");

		gadget.assertResponseSucceeded(resp);
		Steps++;
		test.log(LogStatus.PASS, Steps + " Make sure response is 200");

		Root root = gadget.getResponse();
		assertTrue(root.data.size() == 10);
		Steps++;
		test.log(LogStatus.PASS, Steps + " Make sure All 10 products are returned");

	}

	@Override
	protected void cleanup() throws Exception {
		// TODO Auto-generated method stub
		report.endTest(test);

	}

}
