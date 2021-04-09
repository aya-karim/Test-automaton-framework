package TestCases;

import org.w3c.dom.Node;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import DTOs.products.Root;
import Report.Report;
import dataAccess.XMLDataReader;
import io.restassured.response.Response;
import restAssuredInterfacing.ApiGadget;

public class GetProductOfType extends TestCaseTemplate {
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
		test = report.createTestCaseInReport("Get Product of Type 'Hard Good' ");
		gadget = new ApiGadget();
		Steps = 0;
	}

	@Override
	protected void execution() throws Throwable {
		// TODO Auto-generated method stub
		gadget.createBaseRequest();
		gadget.SetParameters("type", "HardGood");
		gadget.addParametersToRequest();
		Response resp = gadget.createRequest("/products");

		Steps++;
		test.log(LogStatus.PASS, Steps + " Create API");

		gadget.assertResponseSucceeded(resp);
		Steps++;
		test.log(LogStatus.PASS, Steps + " Make sure response is 200");

		Root root = gadget.getResponse();
		for (int i = 0; i < root.data.size() - 1; i++) {
			if (!root.data.get(i).type.equals("HardGood")) {
				Steps++;
				test.log(LogStatus.FAIL, Steps + " Make sure all data are HardGood");
				throw new Exception("Not all data of the same type");

			}
		}
		Steps++;
		test.log(LogStatus.PASS, Steps + " Make sure all data are HardGood");

	}

	@Override
	protected void cleanup() throws Exception {
		// TODO Auto-generated method stub
		report.endTest(test);

	}

}
