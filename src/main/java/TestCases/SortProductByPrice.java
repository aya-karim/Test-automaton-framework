package TestCases;

import org.w3c.dom.Node;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import DTOs.products.Root;
import Report.Report;
import dataAccess.XMLDataReader;
import io.restassured.response.Response;
import restAssuredInterfacing.ApiGadget;

public class SortProductByPrice extends TestCaseTemplate {
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
		test = report.createTestCaseInReport("Sort Products By price");
		gadget = new ApiGadget();
		Steps = 0;
	}

	@Override
	protected void execution() throws Throwable {
		// TODO Auto-generated method stub
		gadget.createBaseRequest();
		gadget.SetParameters("$sort[price]", "-1");
		gadget.addParametersToRequest();
		Response resp = gadget.createRequest("/products");

		Steps++;
		test.log(LogStatus.PASS, Steps + " Create API");

		gadget.assertResponseSucceeded(resp);
		Steps++;
		test.log(LogStatus.PASS, Steps + " Make sure response is 200");

		Root root = gadget.getResponse();
		for (int i = 0; i < root.data.size() - 1; i++) {
			if (root.data.get(i).price < root.data.get(i + 1).price) {
				Steps++;
				test.log(LogStatus.FAIL, Steps + " Products are sorted descendingly");
				throw new Exception("Data is not sorted correctly");

			}
		}
		Steps++;
		test.log(LogStatus.PASS, Steps + " Products are sorted descendingly");

	}

	@Override
	protected void cleanup() throws Exception {
		// TODO Auto-generated method stub
		report.endTest(test);

	}

}
