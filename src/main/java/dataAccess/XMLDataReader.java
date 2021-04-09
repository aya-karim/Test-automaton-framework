package dataAccess;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author akarim
 * 
 *         Read All needed Identifers and Data from XMl in one Static Single
 *         Document used across all Test Cases. Using Singletone Design Pattern.
 */
public class XMLDataReader {
	static XMLDataReader readerInstance = null;
	String filePath;
	private static Document document;
	static NodeList nodeList;
	/**
	 * Constructor that Open the XML Documents and initilize it for the first Time
	 */
	private XMLDataReader() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
//		File file = new File(".\\..\\Vodafone\\src\\main\\resources\\TestAutomationData.xml");
		File file = new File(
				".//src//main//resources//TestAutomationData.xml");
		filePath = file.getCanonicalPath();
		document = builder.parse(new File(filePath));
		document.getDocumentElement().normalize();
		// System.out.println(filePath);
	}

	/**
	 * Returns the Single Instance created or Create it if needed.
	 */
	public static XMLDataReader getInstance() throws Exception {
		if (readerInstance == null) {
			synchronized (XMLDataReader.class) {
				if (readerInstance == null) {
					readerInstance = new XMLDataReader();
				}
			}
		}
		return readerInstance;
	}

	/**
	 * @return the List of All Test data Throws Exception if Data not found.
	 */
	public Node getData() {
		return document.getElementsByTagName("TestData").item(0);

	}

	/**
	 * @return the List of All Web Elements needed in All test cases
	 */
	public Node getIdentifers() {
		return document.getElementsByTagName("WebElements").item(0);

	}

	/**
	 * @param List to search in, the Element to be found.
	 * @return the Value of a specific tag in XMl. Or Not founf if couldn't find the
	 *         Needed element.
	 */
	public String getElementValue(Node node, String elementId) {
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			if (list.item(i).hasAttributes()
					&& list.item(i).getAttributes().item(0).getTextContent().matches(elementId)) {
				return list.item(i).getTextContent().trim();
			}
		}
		return "Not found!";
	}

	/**
	 * @param List to search in, the Element to be found.
	 * @return the the Name tag of a specific Element Or Not founf if couldn't find
	 *         the Needed element
	 */
	public String getElementIdentifier(Node node, String elementId) {
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			if (list.item(i).hasAttributes()
					&& list.item(i).getAttributes().item(0).getTextContent().matches(elementId)) {
				return list.item(i).getChildNodes().item(1).getNodeName();
			}
		}
		return "Not found!";
	}
	public String chooseBrowser()
	{
		  return document.getElementsByTagName("Browser").item(0).getTextContent();
		
	}
}