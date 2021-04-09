package restAssuredInterfacing;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.w3c.dom.Node;

import DTOs.products.Root;
import dataAccess.XMLDataReader;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiGadget {
	static XMLDataReader readerInstance;
	static Node nData;
	Response response;
	static RequestSpecification request;
	Map<String, Object> parameters;

	public ApiGadget() throws Exception {
		readerInstance = XMLDataReader.getInstance();
		nData = readerInstance.getData();
		parameters = new HashMap<String, Object>();
	}

	public static RequestSpecification createBaseRequest() throws Exception {
		request = given().baseUri(readerInstance.getElementValue(nData, "API"))
				.header(new Header("Connection", "keep-alive"));
		return request;
	}

	public Response createRequest(String url) throws Throwable {
		response = request.basePath(url).get().then().extract().response();
		return response;
	}

	public void SetParameters(String key, Object value) {
		parameters.put(key, value);

	}

	public void addParametersToRequest() {
		request.formParams(parameters);

	}

	public static void assertResponseSucceeded(Response response) {
		assertEquals(HttpStatus.SC_OK, response.getStatusCode());
	}

	public Root getResponse() throws Throwable, Throwable {
		Root dto;
		String JsonResponse = response.getBody().asString();
		ObjectMapper om = new ObjectMapper();
		dto = om.readValue(JsonResponse, Root.class);
		return dto;
	}

}
